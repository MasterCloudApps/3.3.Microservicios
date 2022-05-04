package es.codeurjc.monolith.services.impl;

import es.codeurjc.monolith.dtos.requests.CustomerRequestDto;
import es.codeurjc.monolith.dtos.responses.CustomerResponseDto;
import es.codeurjc.monolith.exceptions.CustomerNotFoundException;
import es.codeurjc.monolith.models.Customer;
import es.codeurjc.monolith.repositories.CustomerRepository;
import es.codeurjc.monolith.services.CustomerService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Mapper mapper;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(Mapper mapper, CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }

    public Collection<CustomerResponseDto> findAll() {
        return this.customerRepository.findAll().stream()
                .map(customer -> this.mapper.map(customer, CustomerResponseDto.class))
                .collect(Collectors.toList());
    }

    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        Customer customer = this.mapper.map(customerRequestDto, Customer.class);
        customer = this.customerRepository.save(customer);
        return this.mapper.map(customer, CustomerResponseDto.class);
    }

    public CustomerResponseDto findById(long orderId) {
        Customer customer = this.customerRepository.findById(orderId).orElseThrow(CustomerNotFoundException::new);
        return this.mapper.map(customer, CustomerResponseDto.class);
    }

    public CustomerResponseDto updateStatus(long userId, CustomerRequestDto customerRequestDto) {
        Customer customer = this.customerRepository.findById(userId).orElseThrow(CustomerNotFoundException::new);
        if (!customer.getStatus().equalsIgnoreCase(customerRequestDto.getStatus())) {
            customer.setStatus(customerRequestDto.getStatus());
            customer = this.customerRepository.save(customer);
        }
        return this.mapper.map(customer, CustomerResponseDto.class);
    }

    public CustomerResponseDto delete(long orderId) {
        Customer customer = this.customerRepository.findById(orderId).orElseThrow(CustomerNotFoundException::new);
        this.customerRepository.delete(customer);
        return this.mapper.map(customer, CustomerResponseDto.class);
    }

}
