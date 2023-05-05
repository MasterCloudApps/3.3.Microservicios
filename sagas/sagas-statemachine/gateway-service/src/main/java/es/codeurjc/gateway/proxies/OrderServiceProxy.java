package es.codeurjc.gateway.proxies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import es.codeurjc.gateway.exception.EntityNotFoundException;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceProxy {

	private final WebClient client;

	@Value("#{'${product.url:http://localhost:8081}'}")
    private String orderServiceUrl;
	  
	@Autowired
	public OrderServiceProxy(WebClient client) {
		this.client = client;
	}

	public Mono<OrderInfo> findOrderById(String orderId) {
		Mono<ClientResponse> response = client.get()
				.uri(orderServiceUrl + "/api/v1/orders/{orderId}", orderId).exchange();
		return response.flatMap(resp -> {
			switch (resp.statusCode()) {
			case OK:
				return resp.bodyToMono(OrderInfo.class);
			case NOT_FOUND:
				return Mono.error(new EntityNotFoundException());
			default:
				return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
			}
		});
	}
}
