package es.codeurjc.orderservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.model.events.dto.OrderDetailsDto;

@Component
public class OrderToOrderDetailsDtoConverter implements Converter<Order, OrderDetailsDto> {

	@Override
	public OrderDetailsDto convert(Order source) {
		return new OrderDetailsDto.Builder()
				                  .withOrderId(source.getId()) 
				                  .withCustomerId(source.getOrderDetails().getCustomerId())
				                  .withOrderTotal(source.getOrderDetails().getOrderTotal())
				                  .build();
	}

}
