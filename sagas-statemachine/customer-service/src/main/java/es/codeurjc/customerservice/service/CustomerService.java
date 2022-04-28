package es.codeurjc.customerservice.service;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.customerservice.common.Money;
import es.codeurjc.customerservice.domain.Customer;
import es.codeurjc.customerservice.domain.CustomerNotFoundException;
import es.codeurjc.customerservice.dto.CreateCustomerRequest;
import es.codeurjc.customerservice.dto.CreateCustomerResponse;
import es.codeurjc.customerservice.dto.CustomerResponse;
import es.codeurjc.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void reserveCredit(UUID customerId, UUID orderId, Money orderTotal) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customer.reserveCredit(orderId, orderTotal);
	}

	public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
		final Money money = new Money(createCustomerRequest.getCreditLimit());
		final Customer customer = new Customer(createCustomerRequest.getName(), money);
		Customer customerSaved = customerRepository.save(customer);
		return new CreateCustomerResponse(customerSaved.getId());
	}

	public CustomerResponse getCustomer(UUID customerId) {
		final Customer customer = customerRepository.findById(customerId).orElseThrow(() ->new EntityNotFoundException());
		return toCustomerResponse(customer);
	}
	
	private CustomerResponse toCustomerResponse(final Customer customer) {
		return new CustomerResponse.Builder().withId(customer.getId())
				                             .withName(customer.getName())
				                             .withCreditLimit(customer.getCreditLimit().getAmount())
				                             .build(); 
	}
}
