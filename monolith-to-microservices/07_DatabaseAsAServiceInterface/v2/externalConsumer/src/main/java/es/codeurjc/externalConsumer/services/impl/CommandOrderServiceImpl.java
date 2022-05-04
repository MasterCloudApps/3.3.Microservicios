package es.codeurjc.externalConsumer.services.impl;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.dtos.responses.OrderResponseDto;
import es.codeurjc.externalConsumer.exceptions.OrderNotFoundException;
import es.codeurjc.externalConsumer.services.CommandOrderService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class CommandOrderServiceImpl implements CommandOrderService {

    private Mapper mapper;
    private String ordersServiceUrl;
    private RestTemplate restTemplate;

    public CommandOrderServiceImpl(Mapper mapper, @Value("${orders.service.url}") String ordersServiceUrl, RestTemplate restTemplate) {
        this.mapper = mapper;
        this.ordersServiceUrl = ordersServiceUrl + "/api/v1/orders/";
        this.restTemplate = restTemplate;
    }

    public OrderResponseDto save(OrderRequestDto orderRequestDto) {

        ResponseEntity<OrderResponseDto> orderResponseDto = restTemplate.postForEntity(
                ordersServiceUrl,
                orderRequestDto,
                OrderResponseDto.class);

        return orderResponseDto.getBody();
    }


    public OrderResponseDto delete(long orderId) {
        /*OrderResponseDto order = this.findById(orderId);

        if (order == null) {
            throw new OrderNotFoundException();
        }*/

        ResponseEntity<OrderResponseDto> responseEntity = restTemplate.exchange(
                ordersServiceUrl + "/" + orderId,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {
                });

        return this.mapper.map(responseEntity, OrderResponseDto.class);
    }


}
