package es.codeurjc.externalConsumer.services.impl;

import es.codeurjc.externalConsumer.dtos.responses.OrderResponseDto;
import es.codeurjc.externalConsumer.services.QueryOrderService;
import es.codeurjc.externalConsumer.repositories.OrderRepository;
import es.codeurjc.externalConsumer.exceptions.OrderNotFoundException;
import es.codeurjc.externalConsumer.models.Order;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class QueryOrderServiceImpl implements QueryOrderService {

    private Mapper mapper;
    private OrderRepository orderRepository;

    public QueryOrderServiceImpl(Mapper mapper, OrderRepository orderRepository) {
        this.mapper = mapper;
        this.orderRepository = orderRepository;
    }

    public Collection<OrderResponseDto> findAll() {
        return this.orderRepository.findAll().stream()
                .map(order -> this.mapper.map(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    public OrderResponseDto findById(long orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return this.mapper.map(order, OrderResponseDto.class);
    }
}
