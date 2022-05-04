package es.codeurjc.orders.services.impl;

import es.codeurjc.orders.dtos.requests.OrderRequestDto;
import es.codeurjc.orders.dtos.responses.OrderResponseDto;
import es.codeurjc.orders.exceptions.OrderNotFoundException;
import es.codeurjc.orders.models.Order;
import es.codeurjc.orders.repositories.OrderRepository;
import es.codeurjc.orders.services.OrderService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private Mapper mapper;
    private OrderRepository orderRepository;

    public OrderServiceImpl(Mapper mapper, OrderRepository orderRepository) {
        this.mapper = mapper;
        this.orderRepository = orderRepository;
    }

    public Collection<OrderResponseDto> findAll() {
        return this.orderRepository.findAll().stream()
                .map(order -> this.mapper.map(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    public OrderResponseDto save(OrderRequestDto orderRequestDto) {
        Order order = this.mapper.map(orderRequestDto, Order.class);
        order = this.orderRepository.save(order);
        return this.mapper.map(order, OrderResponseDto.class);
    }

    public OrderResponseDto findById(long orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return this.mapper.map(order, OrderResponseDto.class);
    }


    public OrderResponseDto delete(long orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        this.orderRepository.delete(order);
        return this.mapper.map(order, OrderResponseDto.class);
    }

}
