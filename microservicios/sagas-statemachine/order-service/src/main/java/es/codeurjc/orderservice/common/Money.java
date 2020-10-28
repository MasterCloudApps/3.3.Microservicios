package es.codeurjc.orderservice.common;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Money {

  public static final Money ZERO = new Money(0);
  private BigDecimal amount;

  public Money() {
  }

  public Money(int i) {
    this.amount = new BigDecimal(i);
  }
  
  public Money(String s) {
    this.amount = new BigDecimal(s);
  }

  public Money(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public boolean isGreaterThanOrEqual(Money other) {
    return amount.compareTo(other.amount) >= 0;
  }

  public Money add(Money other) {
    return new Money(amount.add(other.amount));
  }
  
  public Money subtract(Money other) {
    return new Money(amount.subtract(other.amount));
  }
}