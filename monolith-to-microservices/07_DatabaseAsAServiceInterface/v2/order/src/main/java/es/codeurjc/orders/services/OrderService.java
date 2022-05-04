package es.codeurjc.orders.services;

import es.codeurjc.orders.dtos.requests.OrderRequestDto;
import es.codeurjc.orders.dtos.responses.OrderResponseDto;

import java.util.Collection;

public interface OrderService {

    Collection<OrderResponseDto> findAll();

    OrderResponseDto save(OrderRequestDto orderRequestDto);

    OrderResponseDto findById(long userId);

    OrderResponseDto delete(long userId);

}
