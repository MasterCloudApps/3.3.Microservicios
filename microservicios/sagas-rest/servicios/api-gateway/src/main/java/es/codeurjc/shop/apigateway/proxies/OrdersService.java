package es.codeurjc.shop.apigateway.proxies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OrdersService {

	@Value(value = "${orders.url}")
	private String ordersBaseUrl;
	
    public Mono<Order> fetchOrder(long orderId) {
        
    	Mono<ClientResponse> response = WebClient.create(ordersBaseUrl)
                .get()
                .uri("/orders/{orderId}", orderId)
                .exchange();
        
        return response.flatMap(resp -> {
            switch (resp.statusCode()) {
                case OK:
                    return resp.bodyToMono(Order.class);
                case NOT_FOUND:
                    return Mono.error(new OrderNotFoundException());
                default:
                    return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
            }
        });
    }


}