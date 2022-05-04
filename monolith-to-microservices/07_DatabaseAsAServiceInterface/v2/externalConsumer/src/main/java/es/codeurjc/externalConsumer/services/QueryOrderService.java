package es.codeurjc.externalConsumer.services;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.dtos.responses.OrderResponseDto;

import java.util.Collection;

public interface QueryOrderService {

    Collection<OrderResponseDto> findAll();

    OrderResponseDto findById(long userId);

}
