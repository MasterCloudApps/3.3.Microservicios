package es.codeurjc.externalConsumer.services;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;

public interface OrderMapperKafkaService {
  
  void create(OrderRequestDto orderRequestDto);
  
  void delete(Long orderId);
 
}