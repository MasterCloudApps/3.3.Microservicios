package es.codeurjc.shop.apigateway.proxies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ProductsService {

	@Value(value = "${products.url}")
	private String productsBaseUrl;
	
    public Mono<Product> fetchProduct(long productId) {
        
    	Mono<ClientResponse> response = WebClient.create(productsBaseUrl)
                .get()
                .uri( "/products/{productId}", productId)
                .exchange();
        
    	return response.flatMap(resp -> {
            switch (resp.statusCode()) {
                case OK:
                    return resp.bodyToMono(Product.class);
                case NOT_FOUND:
                    return Mono.error(new ProductNotFoundException());
                default:
                    return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
            }
        });
    }
}