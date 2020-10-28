package es.codeurjc.orderservice.dto;


import java.math.BigDecimal;
import java.util.UUID;

public class CreateOrderRequest {
	
  private BigDecimal orderTotal;
  private UUID customerId;
  private String productName;
  private String productReference;
  private Integer quantity;
  
  public BigDecimal getOrderTotal() {
      return orderTotal;
  }

  public UUID getCustomerId() {
      return customerId;
  }

  public String getProductName() {
      return productName;
  }

  public String getProductReference() {
      return productReference;
  }

  public Integer getQuantity() {
      return quantity;
  }

  public static final class Builder {

      private final CreateOrderRequest object;

      public Builder() {
          object = new CreateOrderRequest();
      }

      public Builder withOrderTotal(BigDecimal value) {
          object.orderTotal = value;
          return this;
      }

      public Builder withCustomerId(UUID value) {
          object.customerId = value;
          return this;
      }

      public Builder withProductName(String value) {
          object.productName = value;
          return this;
      }

      public Builder withProductReference(String value) {
          object.productReference = value;
          return this;
      }

      public Builder withQuantity(Integer value) {
          object.quantity = value;
          return this;
      }

      public CreateOrderRequest build() {
          return object;
      }

  }
}
