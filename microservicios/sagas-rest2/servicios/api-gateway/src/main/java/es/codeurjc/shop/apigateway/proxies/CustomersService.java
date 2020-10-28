package es.codeurjc.shop.apigateway.proxies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class CustomersService {

	@Value(value = "${customers.url}")
	private String customersBaseUrl;
	
    public Mono<Customer> fetchCustomer(long customersId) {
        
    	Mono<ClientResponse> response = WebClient.create(customersBaseUrl)
                .get()
                .uri("/customers/{customersId}", customersId)
                .exchange();
        
        return response.flatMap(resp -> {
            switch (resp.statusCode()) {
                case OK:
                    return resp.bodyToMono(Customer.class);
                case NOT_FOUND:
                    return Mono.error(new OrderNotFoundException());
                default:
                    return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
            }
        });
    }


}