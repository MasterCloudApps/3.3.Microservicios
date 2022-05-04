package es.codeurjc.orderservice.sm;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.repository.OrderRepository;
import es.codeurjc.orderservice.service.OrderManager;
import es.codeurjc.orderservice.types.OrderEventEnum;
import es.codeurjc.orderservice.types.OrderStatusEnum;




@Component
public class OrderStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderStatusEnum, OrderEventEnum> {

	private Logger log = LoggerFactory.getLogger(OrderStateChangeInterceptor.class);
	
	private final OrderRepository orderRepository;
	
	@Autowired
	public OrderStateChangeInterceptor(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
    @Transactional
    @Override
    public void preStateChange(State<OrderStatusEnum, OrderEventEnum> state, Message<OrderEventEnum> message, Transition<OrderStatusEnum, OrderEventEnum> transition, StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine) {
        log.debug("Pre-State Change");

        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault(OrderManager.ORDER_ID_HEADER, " ")))
                .ifPresent(orderId -> {
                	
                    log.debug("Saving state for order id: " + orderId + " Status: " + state.getId());

                    Order order = orderRepository.getOne(UUID.fromString(orderId));
                    
                    order.setState(state.getId());
                    orderRepository.saveAndFlush(order);
                });
    }
	
}
