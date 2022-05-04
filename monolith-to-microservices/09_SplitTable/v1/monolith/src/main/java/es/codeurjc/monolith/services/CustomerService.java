package es.codeurjc.monolith.services;

import es.codeurjc.monolith.dtos.requests.CustomerRequestDto;
import es.codeurjc.monolith.dtos.responses.CustomerResponseDto;

import java.util.Collection;

public interface CustomerService {

    Collection<CustomerResponseDto> findAll();

    CustomerResponseDto save(CustomerRequestDto customerRequestDto);

    CustomerResponseDto findById(long userId);

    CustomerResponseDto updateStatus(long userId, CustomerRequestDto customerRequestDto);

    CustomerResponseDto delete(long userId);

}
