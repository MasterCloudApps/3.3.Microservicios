package es.codeurjc.employees.services;

import es.codeurjc.employees.dtos.requests.EmployeeRequestDto;
import es.codeurjc.employees.dtos.responses.EmployeeResponseDto;

import java.util.Collection;

public interface EmployeeService {

    Collection<EmployeeResponseDto> findAll();

    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto findById(long userId);

    EmployeeResponseDto delete(long userId);

}
