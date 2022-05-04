package es.codeurjc.shop.apigateway;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import es.codeurjc.shop.apigateway.orders.DetailedOrderHandler;

@SpringBootApplication
public class Application {

	@Value(value = "${orders.url}")
	private String ordersBaseUrl;
	
	@Value(value = "${products.url}")
	private String productsBaseUrl;
	
	@Value(value = "${customers.url}")
	private String customersBaseUrl;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        
    	return builder.routes()
    			
                .route(r -> r
                        .path("/products/**")
                        .uri(productsBaseUrl))
                
                .route(r -> r
                        .path("/customers/**")
                        .uri(customersBaseUrl))
                
                .route(r -> r
                        .path("/orders/**")
                        .uri(ordersBaseUrl))
                
        .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderHandlerRouting(DetailedOrderHandler orderHandlers) {
        return RouterFunctions.route(
        		GET("/detailedorders/{orderId}"), 
        		orderHandlers::getDetailedOrder);
    }

    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
