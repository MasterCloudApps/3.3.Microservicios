package es.codeurjc.externalConsumer.services;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.dtos.responses.OrderResponseDto;

import java.util.Collection;

public interface CommandOrderService {

    OrderResponseDto save(OrderRequestDto orderRequestDto);

    OrderResponseDto delete(long userId);

}
