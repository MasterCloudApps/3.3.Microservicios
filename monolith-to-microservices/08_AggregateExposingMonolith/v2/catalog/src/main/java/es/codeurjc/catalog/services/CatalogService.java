package es.codeurjc.catalog.services;

import java.util.Collection;

import es.codeurjc.catalog.dtos.requests.EmployeeRequestDto;
import es.codeurjc.catalog.dtos.responses.EmployeeResponseDto;

public interface CatalogService {

    Collection<EmployeeResponseDto> findAll();

    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto findById(long userId);

    EmployeeResponseDto delete(long userId);

}
