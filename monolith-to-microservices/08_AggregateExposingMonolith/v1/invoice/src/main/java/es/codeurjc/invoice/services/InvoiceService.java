package es.codeurjc.invoice.services;

import java.util.Collection;

import es.codeurjc.invoice.dtos.requests.EmployeeRequestDto;
import es.codeurjc.invoice.dtos.responses.EmployeeResponseDto;

public interface InvoiceService {

    Collection<EmployeeResponseDto> findAll();

    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto findById(long userId);

    EmployeeResponseDto delete(long userId);

}
