package es.codeurjc.customerservice.domain;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import es.codeurjc.customerservice.common.Money;

@Entity
@Table(name = "Customer")
@Access(AccessType.FIELD)
public class Customer {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
	private UUID id;

    @Column(unique = true)
	private String name;

	@Embedded
	private Money creditLimit;

	@ElementCollection
	private Map<UUID, Money> creditReservations;


	public Customer() {
	}

	public Customer(String name, Money creditLimit) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.creditReservations = Collections.emptyMap();
	}

	public UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Money getCreditLimit() {
		return creditLimit;
	}

	public Map<UUID, Money> getCreditReservations() {
		return creditReservations;
	}


	public void reserveCredit(UUID orderId, Money orderTotal) {
		if (availableCredit().isGreaterThanOrEqual(orderTotal)) {
			creditReservations.put(orderId, orderTotal);
		} else
			throw new CustomerCreditLimitExceededException();
	}
	

	private Money availableCredit() {
		return creditLimit.subtract(creditReservations.values().stream().reduce(Money.ZERO, Money::add));
	}

}
