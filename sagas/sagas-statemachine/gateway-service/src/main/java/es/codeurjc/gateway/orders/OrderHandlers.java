package es.codeurjc.gateway.orders;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import es.codeurjc.gateway.exception.EntityNotFoundException;
import es.codeurjc.gateway.proxies.OrderInfo;
import es.codeurjc.gateway.proxies.OrderServiceProxy;
import es.codeurjc.gateway.proxies.ProductInfo;
import es.codeurjc.gateway.proxies.ProductServiceProxy;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public class OrderHandlers {

	private final OrderServiceProxy orderService;

	private final ProductServiceProxy productService;

	public OrderHandlers(OrderServiceProxy orderService, ProductServiceProxy productService) {
		this.orderService = orderService;
		this.productService = productService;
	}

	public Mono<ServerResponse> getOrderDetails(ServerRequest serverRequest) {

		final String orderId = serverRequest.pathVariable("orderId");
		
		Mono<OrderInfo> orderInfo = orderService.findOrderById(orderId);

		Mono<ProductInfo> productInfo = orderInfo.flatMap(o -> productService.findProductById(o.getProductId()));			

		Mono<Tuple2<OrderInfo, ProductInfo>> combined = Mono.zip(orderInfo, productInfo);

		Mono<OrderDetails> orderDetails = combined.map(OrderDetails::makeOrderDetails);

		return orderDetails
				.flatMap(od -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(od)))
				.onErrorResume(EntityNotFoundException.class, e -> ServerResponse.notFound().build());
	}
}
