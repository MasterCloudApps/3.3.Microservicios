package es.codeurjc.customerservice.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.customerservice.dto.CreateCustomerRequest;
import es.codeurjc.customerservice.dto.CreateCustomerResponse;
import es.codeurjc.customerservice.dto.CustomerResponse;
import es.codeurjc.customerservice.service.CustomerService;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("customers")
	public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		return new ResponseEntity<>(customerService.createCustomer(createCustomerRequest),HttpStatus.CREATED);
	}
	
    @GetMapping("customers/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable(value = "customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
    }	
}
