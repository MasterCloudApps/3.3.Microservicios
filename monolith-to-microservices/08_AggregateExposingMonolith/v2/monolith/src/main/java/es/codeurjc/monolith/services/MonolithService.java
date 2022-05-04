package es.codeurjc.monolith.services;

import java.util.Collection;

import es.codeurjc.monolith.dtos.requests.EmployeeRequestDto;
import es.codeurjc.monolith.dtos.responses.EmployeeResponseDto;

public interface MonolithService {

    Collection<EmployeeResponseDto> findAll();

    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto findById(long userId);

    EmployeeResponseDto delete(long userId);

}
