package es.codeurjc.finance.services.impl;

import es.codeurjc.finance.dtos.requests.FinanceRequestDto;
import es.codeurjc.finance.dtos.responses.FinanceResponseDto;
import es.codeurjc.finance.exceptions.CustomerNotFoundException;
import es.codeurjc.finance.services.FinanceService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class FinanceServiceImpl implements FinanceService {

    private Mapper mapper;
    private String customerServiceUrl;
    private RestTemplate restTemplate;

    public FinanceServiceImpl(Mapper mapper, @Value("${customer.url}") String customerServiceUrl, RestTemplate restTemplate) {
        this.mapper = mapper;
        this.customerServiceUrl = customerServiceUrl + "/api/v1/customer/";
        this.restTemplate = restTemplate;
    }

    public Collection<FinanceResponseDto> findAll() {
        ResponseEntity<Collection<FinanceResponseDto>> responseEntity = restTemplate.exchange(
                customerServiceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public FinanceResponseDto save(FinanceRequestDto financeRequestDto) {

        ResponseEntity<FinanceResponseDto> financeResponseDto = restTemplate.postForEntity(
                customerServiceUrl,
                financeRequestDto,
                FinanceResponseDto.class);

        return financeResponseDto.getBody();
    }

    public FinanceResponseDto findById(long orderId) {
        ResponseEntity<FinanceResponseDto> responseEntity = restTemplate.exchange(
                customerServiceUrl + orderId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public FinanceResponseDto updateStatus(long userId, FinanceRequestDto financeRequestDto) {

        ResponseEntity<FinanceResponseDto> financeResponseDto = restTemplate.postForEntity(
                customerServiceUrl + userId,
                financeRequestDto,
                FinanceResponseDto.class);

        return financeResponseDto.getBody();
    }

    public FinanceResponseDto delete(long employId) {
        FinanceResponseDto order = this.findById(employId);

        if (order == null) {
            throw new CustomerNotFoundException();
        }

        ResponseEntity<FinanceResponseDto> responseEntity = restTemplate.exchange(
                customerServiceUrl + "/" + employId,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {
                });

        return this.mapper.map(responseEntity, FinanceResponseDto.class);
    }


}
