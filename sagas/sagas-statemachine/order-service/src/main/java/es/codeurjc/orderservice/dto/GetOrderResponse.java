package es.codeurjc.orderservice.dto;


import java.util.UUID;

import es.codeurjc.orderservice.types.OrderStatusEnum;
import es.codeurjc.orderservice.types.RejectionReasonEnum;

public class GetOrderResponse {
  
  private UUID orderId;
  private String productId;
  private OrderStatusEnum orderState;
  private RejectionReasonEnum rejectionReason;

  public GetOrderResponse() {
  }

  public GetOrderResponse(UUID orderId, String productId, OrderStatusEnum orderState, RejectionReasonEnum rejectionReason) {
    this.orderId = orderId;
    this.productId = productId;
    this.orderState = orderState;
    this.rejectionReason = rejectionReason;
  }

  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }

  public String getProductId() {
      return productId;
  }

  public void setProductId(String productId) {
      this.productId = productId;
  }

  public OrderStatusEnum getOrderState() {
    return orderState;
  }

  public void setOrderState(OrderStatusEnum orderState) {
    this.orderState = orderState;
  }

  public RejectionReasonEnum getRejectionReason() {
    return rejectionReason;
  }

  public void setRejectionReason(RejectionReasonEnum rejectionReason) {
    this.rejectionReason = rejectionReason;
  }
}
