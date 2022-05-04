package es.codeurjc.monolith.services.impl;

import es.codeurjc.monolith.dtos.requests.EmployeeRequestDto;
import es.codeurjc.monolith.dtos.responses.EmployeeResponseDto;
import es.codeurjc.monolith.exceptions.EmployeeNotFoundException;
import es.codeurjc.monolith.services.MonolithService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class MonolithServiceImpl implements MonolithService {

    private Mapper mapper;
    private String employServiceUrl;
    private RestTemplate restTemplate;

    public MonolithServiceImpl(Mapper mapper, @Value("${employee.url}") String employServiceUrl, RestTemplate restTemplate) {
        this.mapper = mapper;
        this.employServiceUrl = employServiceUrl + "/api/v1/employ/";
        this.restTemplate = restTemplate;
    }

    public Collection<EmployeeResponseDto> findAll() {
        ResponseEntity<Collection<EmployeeResponseDto>> responseEntity = restTemplate.exchange(
                employServiceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public EmployeeResponseDto save(EmployeeRequestDto orderRequestDto) {

        ResponseEntity<EmployeeResponseDto> orderResponseDto = restTemplate.postForEntity(
                employServiceUrl,
                orderRequestDto,
                EmployeeResponseDto.class);

        return orderResponseDto.getBody();
    }

    public EmployeeResponseDto findById(long orderId) {
        ResponseEntity<EmployeeResponseDto> responseEntity = restTemplate.exchange(
                employServiceUrl + "/" + orderId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public EmployeeResponseDto delete(long employId) {
        EmployeeResponseDto order = this.findById(employId);

        if (order == null) {
            throw new EmployeeNotFoundException();
        }

        ResponseEntity<EmployeeResponseDto> responseEntity = restTemplate.exchange(
                employServiceUrl + "/" + employId,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {
                });

        return this.mapper.map(responseEntity, EmployeeResponseDto.class);
    }


}
