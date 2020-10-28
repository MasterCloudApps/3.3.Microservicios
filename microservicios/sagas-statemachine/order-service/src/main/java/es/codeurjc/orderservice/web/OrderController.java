package es.codeurjc.orderservice.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.orderservice.dto.CreateOrderRequest;
import es.codeurjc.orderservice.dto.CreateOrderResponse;
import es.codeurjc.orderservice.dto.GetOrderResponse;
import es.codeurjc.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {
	
	private final OrderService orderService; 
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

    @PostMapping("orders")
	public ResponseEntity<CreateOrderResponse> placeOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return new ResponseEntity<>(orderService.placeOrder(createOrderRequest),HttpStatus.CREATED);
	}
	
    @GetMapping("orders/{orderId}")
	public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("orderId") UUID orderId) {
    	return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }
}
