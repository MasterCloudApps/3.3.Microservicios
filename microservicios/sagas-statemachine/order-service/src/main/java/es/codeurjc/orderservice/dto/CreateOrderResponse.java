package es.codeurjc.orderservice.dto;

import java.util.UUID;

public class CreateOrderResponse {
	
  private UUID orderId;

  public CreateOrderResponse() {
  }

  public CreateOrderResponse(UUID orderId) {
    this.orderId = orderId;
  }

  public UUID getOrderId() {
    return orderId;
  }
}
