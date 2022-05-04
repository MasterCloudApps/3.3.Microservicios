package es.codeurjc.orderservice.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.orderservice.common.Money;
import es.codeurjc.orderservice.common.OrderDetails;
import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.dto.CreateOrderRequest;
import es.codeurjc.orderservice.dto.CreateOrderResponse;
import es.codeurjc.orderservice.dto.GetOrderResponse;
import es.codeurjc.orderservice.exception.EntityNotFoundException;
import es.codeurjc.orderservice.repository.OrderRepository;
import es.codeurjc.orderservice.types.OrderStatusEnum;

@Service
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderManager orderManager;
	
	@Autowired
	public OrderService(OrderRepository orderRepository,OrderManager orderManager) {
		this.orderRepository = orderRepository;
		this.orderManager = orderManager;
	}
	
	public CreateOrderResponse placeOrder(CreateOrderRequest createOrderRequest) {
		final OrderDetails orderDetails = new OrderDetails(createOrderRequest.getCustomerId(),new Money(createOrderRequest.getOrderTotal()));
		final Order order = new Order.Builder()
				                     .withId(null)
				                     .withOrderDetails(orderDetails)
				                     .withState(OrderStatusEnum.NEW)
				                     .withName(createOrderRequest.getProductName())
				                     .withQuantity(createOrderRequest.getQuantity())
				                     .withReference(createOrderRequest.getProductReference())
				                     .build();
		
		final Order orderSaved = orderManager.newOrder(order);
		return new CreateOrderResponse(orderSaved.getId());
	}

	public GetOrderResponse getOrder(UUID orderId) {
		
		final Optional<Order> optOrder = orderRepository.findById(orderId);
		if (!optOrder.isPresent()) {
		   throw new EntityNotFoundException();
		}
		final Order order = optOrder.get();
		return new GetOrderResponse(order.getId(), order.getState(), order.getRejectionReason());
	}
}
