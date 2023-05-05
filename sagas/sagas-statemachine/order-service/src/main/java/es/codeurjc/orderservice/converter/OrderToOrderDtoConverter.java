package es.codeurjc.orderservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.model.events.dto.OrderDto;

@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {
	

	@Override
	public OrderDto convert(Order order) {
		return new OrderDto.Builder()
				           .withId(order.getId())
				           .withState(order.getState())
				           .withProductId(order.getProductId())
				           .withQuantity(order.getQuantity())
				           .build();
	}

}
