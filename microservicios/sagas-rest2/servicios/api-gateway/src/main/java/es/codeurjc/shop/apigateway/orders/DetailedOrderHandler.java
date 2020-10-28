package es.codeurjc.shop.apigateway.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import es.codeurjc.shop.apigateway.proxies.Customer;
import es.codeurjc.shop.apigateway.proxies.CustomersService;
import es.codeurjc.shop.apigateway.proxies.Order;
import es.codeurjc.shop.apigateway.proxies.OrdersService;
import es.codeurjc.shop.apigateway.proxies.Product;
import es.codeurjc.shop.apigateway.proxies.ProductsService;
import reactor.core.publisher.Mono;

@Component
public class DetailedOrderHandler {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ProductsService productsService;

	@Autowired
	private CustomersService customersService;

	public Mono<ServerResponse> getDetailedOrder(ServerRequest serverRequest) {

		long orderId = Long.parseLong(serverRequest.pathVariable("orderId"));

		Mono<Order> order = ordersService.fetchOrder(orderId);

		Mono<Product> product = order
				.flatMap(o -> productsService.fetchProduct(o.getProductId()));

		Mono<Customer> customer = order.flatMap(
				o -> customersService.fetchCustomer(o.getCustomerId()));

		Mono<DetailedOrder> detailedOrder = Mono.zip(order,  product, customer)
				.map(t -> new DetailedOrder(t.getT1(), t.getT2(), t.getT3()));
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(detailedOrder, DetailedOrder.class);
	}
}
