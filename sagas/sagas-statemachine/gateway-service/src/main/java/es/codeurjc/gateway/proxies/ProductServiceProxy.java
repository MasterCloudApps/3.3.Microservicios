package es.codeurjc.gateway.proxies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import es.codeurjc.gateway.exception.EntityNotFoundException;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceProxy {

	private final WebClient client;
	
	
	@Value("#{'${product.url:http://localhost:8083}'}")
	private String productServiceUrl;

	@Autowired
	public ProductServiceProxy(WebClient client) {
		this.client = client;
	}

	public Mono<ProductInfo> findProductById(String productId) {
		Mono<ClientResponse> response = client.get()
				.uri(productServiceUrl + "/api/v1/products/{productId}", productId).exchange();
		return response.flatMap(resp -> {
			switch (resp.statusCode()) {
			case OK:
				return resp.bodyToMono(ProductInfo.class);
			case NOT_FOUND:
				return Mono.error(new EntityNotFoundException());
			default:
				return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
			}
		});
	}
}
