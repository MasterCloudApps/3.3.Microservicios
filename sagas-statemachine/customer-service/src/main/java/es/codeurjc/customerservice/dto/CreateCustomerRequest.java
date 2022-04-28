package es.codeurjc.customerservice.dto;

import java.math.BigDecimal;

public class CreateCustomerRequest {
  private String name;
  private BigDecimal creditLimit;

  public CreateCustomerRequest() {
  }

  public CreateCustomerRequest(String name, BigDecimal creditLimit) {

    this.name = name;
    this.creditLimit = creditLimit;
  }


  public String getName() {
    return name;
  }

  public BigDecimal getCreditLimit() {
    return creditLimit;
  }
}
