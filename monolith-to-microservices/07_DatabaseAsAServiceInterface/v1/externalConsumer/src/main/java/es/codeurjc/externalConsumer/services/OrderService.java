package es.codeurjc.externalConsumer.services;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.dtos.responses.OrderResponseDto;

import java.util.Collection;

public interface OrderService {

    Collection<OrderResponseDto> findAll();

    OrderResponseDto save(OrderRequestDto orderRequestDto);

    OrderResponseDto findById(long userId);

    OrderResponseDto delete(long userId);

}
