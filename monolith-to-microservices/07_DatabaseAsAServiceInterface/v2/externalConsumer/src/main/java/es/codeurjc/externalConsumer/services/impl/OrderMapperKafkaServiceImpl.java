package es.codeurjc.externalConsumer.services.impl;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.models.Order;
import es.codeurjc.externalConsumer.repositories.OrderRepository;
import es.codeurjc.externalConsumer.services.OrderMapperKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderMapperKafkaServiceImpl implements OrderMapperKafkaService {

  private final OrderRepository orderRepository;
  private final Mapper mapper;

  public OrderMapperKafkaServiceImpl(OrderRepository orderRepository, Mapper mapper){
    this.orderRepository = orderRepository;
    this.mapper = mapper;
  }
  
  @Override
  public void create(OrderRequestDto orderRequestDto){
    orderRepository.save(this.mapper.map(orderRequestDto, Order.class));
  }
  
  @Override
  public void delete(Long orderId){
    orderRepository.deleteById(orderId);
  }
 
}