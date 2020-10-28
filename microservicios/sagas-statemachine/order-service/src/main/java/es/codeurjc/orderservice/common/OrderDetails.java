package es.codeurjc.orderservice.common;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.hibernate.annotations.Type;

@Embeddable
public class OrderDetails {

  @Type(type="org.hibernate.type.UUIDCharType")
  @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
  private UUID customerId;

  @Embedded
  private Money orderTotal;

  public OrderDetails() {
  }

  public OrderDetails(UUID customerId, Money orderTotal) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
  }

  public UUID getCustomerId() {
      return customerId;
  }

  public Money getOrderTotal() {
      return orderTotal;
  }

  public static final class Builder {

      private final OrderDetails object;

      public Builder() {
          object = new OrderDetails();
      }

      public Builder withCustomerId(UUID value) {
          object.customerId = value;
          return this;
      }

      public Builder withOrderTotal(Money value) {
          object.orderTotal = value;
          return this;
      }

      public OrderDetails build() {
          return object;
      }

  }
  
}
