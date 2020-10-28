package es.codeurjc.orderservice.sm.actions;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import es.codeurjc.orderservice.converter.OrderToOrderDtoConverter;
import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.model.events.dto.OrderDto;
import es.codeurjc.orderservice.repository.OrderRepository;
import es.codeurjc.orderservice.service.OrderManager;
import es.codeurjc.orderservice.stream.kafka.OrderStreamService;
import es.codeurjc.orderservice.types.OrderEventEnum;
import es.codeurjc.orderservice.types.OrderStatusEnum;


@Component
public class AllocateOrderAction implements Action<OrderStatusEnum, OrderEventEnum> {
	
	private Logger log = LoggerFactory.getLogger(AllocateOrderAction.class);
	
	private final OrderRepository orderRepository;
	private final OrderStreamService orderStreamService;
	private final OrderToOrderDtoConverter orderToOrderDtoConverter;
	
	@Autowired
	public AllocateOrderAction(OrderRepository orderRepository,
			                   OrderStreamService orderStreamService,
			                   OrderToOrderDtoConverter orderToOrderDtoConverter) {
		this.orderRepository = orderRepository;
		this.orderStreamService = orderStreamService;
		this.orderToOrderDtoConverter = orderToOrderDtoConverter;
	}

	@Override
	public void execute(StateContext<OrderStatusEnum, OrderEventEnum> context) {
		String orderId = (String) context.getMessage().getHeaders().get(OrderManager.ORDER_ID_HEADER);
		Optional<Order> orderOptional = orderRepository.findById(UUID.fromString(orderId));

		orderOptional.ifPresentOrElse(order -> {
			
			final OrderDto orderDto = orderToOrderDtoConverter.convert(order);
			orderStreamService.sendAllocateRequest(orderDto);
			
		}, () -> log.error("Order Not Found. Id: " + orderId));

		log.debug("Sent Validation request to queue for order id " + orderId);
	}

}
