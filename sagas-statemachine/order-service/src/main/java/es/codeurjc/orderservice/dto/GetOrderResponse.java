package es.codeurjc.orderservice.dto;


import java.util.UUID;

import es.codeurjc.orderservice.types.OrderStatusEnum;
import es.codeurjc.orderservice.types.RejectionReasonEnum;

public class GetOrderResponse {
  private UUID orderId;
  private OrderStatusEnum orderState;
  private RejectionReasonEnum rejectionReason;

  public GetOrderResponse() {
  }

  public GetOrderResponse(UUID orderId, OrderStatusEnum orderState, RejectionReasonEnum rejectionReason) {
    this.orderId = orderId;
    this.orderState = orderState;
    this.rejectionReason = rejectionReason;
  }

  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
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
