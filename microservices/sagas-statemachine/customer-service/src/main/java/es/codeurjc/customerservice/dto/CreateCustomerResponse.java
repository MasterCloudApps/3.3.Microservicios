package es.codeurjc.customerservice.dto;

import java.util.UUID;

public class CreateCustomerResponse {
  private UUID customerId;

  public CreateCustomerResponse() {
  }

  public CreateCustomerResponse(UUID customerId) {
    this.customerId = customerId;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }
}
