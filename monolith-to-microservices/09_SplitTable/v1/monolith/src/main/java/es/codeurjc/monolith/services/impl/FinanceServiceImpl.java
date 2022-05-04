package es.codeurjc.monolith.services.impl;

import es.codeurjc.monolith.dtos.requests.CustomerRequestDto;
import es.codeurjc.monolith.dtos.requests.FinanceRequestDto;
import es.codeurjc.monolith.dtos.responses.CustomerResponseDto;
import es.codeurjc.monolith.dtos.responses.FinanceResponseDto;
import es.codeurjc.monolith.exceptions.CustomerNotFoundException;
import es.codeurjc.monolith.models.Customer;
import es.codeurjc.monolith.repositories.FinanceRepository;
import es.codeurjc.monolith.services.FinanceService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FinanceServiceImpl implements FinanceService {

    private Mapper mapper;
    private FinanceRepository financeRepository;

    public FinanceServiceImpl(Mapper mapper, FinanceRepository financeRepository) {
        this.mapper = mapper;
        this.financeRepository = financeRepository;
    }

    public Collection<FinanceResponseDto> findAll() {
        return this.financeRepository.findAll().stream()
                .map(customer -> this.mapper.map(customer, FinanceResponseDto.class))
                .collect(Collectors.toList());
    }

    public FinanceResponseDto save(FinanceRequestDto financeRequestDto) {
        Customer customer = this.mapper.map(financeRequestDto, Customer.class);
        customer = this.financeRepository.save(customer);
        return this.mapper.map(customer, FinanceResponseDto.class);
    }

    public FinanceResponseDto findById(long orderId) {
        Customer customer = this.financeRepository.findById(orderId).orElseThrow(CustomerNotFoundException::new);
        return this.mapper.map(customer, FinanceResponseDto.class);
    }

    public FinanceResponseDto updateStatus(long userId, FinanceRequestDto financeRequestDto) {
        Customer customer = this.financeRepository.findById(userId).orElseThrow(CustomerNotFoundException::new);
        if (!customer.getStatus().equalsIgnoreCase(financeRequestDto.getStatus())) {
            customer.setStatus(financeRequestDto.getStatus());
            customer = this.financeRepository.save(customer);
        }
        return this.mapper.map(customer, FinanceResponseDto.class);
    }

    public FinanceResponseDto delete(long orderId) {
        Customer customer = this.financeRepository.findById(orderId).orElseThrow(CustomerNotFoundException::new);
        this.financeRepository.delete(customer);
        return this.mapper.map(customer, FinanceResponseDto.class);
    }

}
