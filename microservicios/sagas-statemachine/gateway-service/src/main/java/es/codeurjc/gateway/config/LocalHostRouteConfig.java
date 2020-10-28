package es.codeurjc.gateway.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import es.codeurjc.gateway.orders.OrderHandlers;
import es.codeurjc.gateway.proxies.OrderServiceProxy;
import es.codeurjc.gateway.proxies.ProductServiceProxy;

@Configuration
public class LocalHostRouteConfig {
	
	
    @Bean
    public RouteLocator localHostRoutes(RouteLocatorBuilder builder){
        return builder.routes()
        		.route(r -> r.path("/api/v1/orders/**")
        				.filters(f -> f.rewritePath("/api/v1/orders/(?<segment>.*)", "/api/v1/orders/${segment}"))
        				.uri("http://localhost:8081"))
        		.route(r -> r.path("/api/v1/products/**")
        				.filters(f -> f.rewritePath("/api/v1/products/(?<segment>.*)", "/api/v1/products/${segment}"))
        				.uri("http://localhost:8083"))
        		.route(r -> r.path("/api/v1/customers/**")
        				.filters(f -> f.rewritePath("/api/v1/customers/(?<segment>.*)", "/api/v1/customers/${segment}"))
        				.uri("http://localhost:8082"))
                .build();
    }
    
    
    @Bean
    public RouterFunction<ServerResponse> orderHandlerRouting(OrderHandlers orderHandlers) {
      return RouterFunctions.route(GET("/orders/{orderId}/products/{productId}"), orderHandlers::getOrderDetails);
    }

    @Bean
    public OrderHandlers orderHandlers(OrderServiceProxy orderService, ProductServiceProxy productService) {
      return new OrderHandlers(orderService, productService);
    }    
    
    @Bean
    public WebClient webClient() {
      return WebClient.create();
    }

}
