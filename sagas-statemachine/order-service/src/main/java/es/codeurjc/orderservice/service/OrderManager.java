package es.codeurjc.orderservice.service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.repository.OrderRepository;
import es.codeurjc.orderservice.sm.OrderStateChangeInterceptor;
import es.codeurjc.orderservice.types.OrderEventEnum;
import es.codeurjc.orderservice.types.OrderStatusEnum;

@Service
public class OrderManager {

	private Logger log = LoggerFactory.getLogger(OrderManager.class);

	public static final String ORDER_ID_HEADER = "ORDER_ID_HEADER";

	private final OrderRepository orderRepository;

	private final StateMachineFactory<OrderStatusEnum, OrderEventEnum> stateMachineFactory;
	private final OrderStateChangeInterceptor orderStateChangeInterceptor;

	@Autowired
	public OrderManager(
			StateMachineFactory<OrderStatusEnum, OrderEventEnum> stateMachineFactory,
			OrderRepository orderRepository,
			OrderStateChangeInterceptor orderStateChangeInterceptor) {
		this.stateMachineFactory = stateMachineFactory;
		this.orderRepository = orderRepository;
		this.orderStateChangeInterceptor = orderStateChangeInterceptor;
	}

	@Transactional
	public Order newOrder(Order order) {
		Order orderSaved = orderRepository.saveAndFlush(order);
		sendOrderEvent(orderSaved, OrderEventEnum.ALLOCATE_ORDER);
		return orderSaved;
	}

	public void processAllocationResult(UUID orderId, Boolean isValid) {

		log.debug("Process Validation Result for orderId: " + orderId
				+ " Valid? " + isValid);

		Optional<Order> orderOptional = orderRepository.findById(orderId);

		orderOptional.ifPresentOrElse(order -> {
			if (isValid) {
				sendOrderEvent(order, OrderEventEnum.ALLOCATION_SUCCESS);

				// wait for status change
				awaitForStatus(orderId, OrderStatusEnum.ALLOCATED);

				Order validatedOrder = orderRepository.findById(orderId).get();

				sendOrderEvent(validatedOrder, OrderEventEnum.RESERVE_CREDIT);

			} else {
				sendOrderEvent(order, OrderEventEnum.ALLOCATION_FAILED);
			}
		}, () -> log.error("Order Not Found. Id: " + orderId));
	}

	public void processCreditResult(UUID orderId, Boolean isValid,
			String rejectionReason) {
		log.debug("Process Credit Result for orderId: " + orderId + " Valid? "
				+ isValid + " Reason? " + rejectionReason);

		Optional<Order> orderOptional = orderRepository.findById(orderId);
		orderOptional.ifPresentOrElse(order -> {
			if (isValid) {
				sendOrderEvent(order, OrderEventEnum.CREDIT_APPROVED);

				// wait for status change
				awaitForStatus(orderId, OrderStatusEnum.APPROVED);

			} else {
				sendOrderEvent(order, OrderEventEnum.CREDIT_REJECTED);
			}
		}, () -> log.error("Order Not Found. Id: " + orderId));
	}

	private void sendOrderEvent(Order order, OrderEventEnum eventEnum) {

		StateMachine<OrderStatusEnum, OrderEventEnum> sm = build(order);

		Message<OrderEventEnum> msg = MessageBuilder.withPayload(eventEnum)
				.setHeader(ORDER_ID_HEADER, order.getId().toString()).build();

		sm.sendEvent(msg);
	}

	private void awaitForStatus(UUID orderId, OrderStatusEnum statusEnum) {

		AtomicBoolean found = new AtomicBoolean(false);
		AtomicInteger loopCount = new AtomicInteger(0);

		while (!found.get()) {
			if (loopCount.incrementAndGet() > 10) {
				found.set(true);
				log.debug("Loop Retries exceeded");
			}

			orderRepository.findById(orderId).ifPresentOrElse(order -> {
				if (order.getState().equals(statusEnum)) {
					found.set(true);
					log.debug("Order Found");
				} else {
					log.debug("Order Status Not Equal. Expected: "
							+ statusEnum.name() + " Found: "
							+ order.getState().name());
				}
			}, () -> {
				log.debug("Order Id Not Found");
			});

			if (!found.get()) {
				try {
					log.debug("Sleeping for retry");
					Thread.sleep(100);
				} catch (Exception e) {
					// do nothing
				}
			}
		}
	}

	private StateMachine<OrderStatusEnum, OrderEventEnum> build(Order order) {

		StateMachine<OrderStatusEnum, OrderEventEnum> sm = stateMachineFactory
				.getStateMachine(order.getId());

		sm.stop();

		sm.getStateMachineAccessor().doWithAllRegions(sma -> {
			sma.addStateMachineInterceptor(orderStateChangeInterceptor);
			sma.resetStateMachine(new DefaultStateMachineContext<>(
					order.getState(), null, null, null));
		});

		sm.start();

		return sm;
	}

}
