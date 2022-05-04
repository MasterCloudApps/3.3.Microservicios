package es.codeurjc.employees.services.impl;

import es.codeurjc.employees.dtos.requests.EmployeeRequestDto;
import es.codeurjc.employees.dtos.responses.EmployeeResponseDto;
import es.codeurjc.employees.exceptions.EmployeeNotFoundException;
import es.codeurjc.employees.models.Employee;
import es.codeurjc.employees.repositories.EmployeeRepository;
import es.codeurjc.employees.services.EmployeeService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Mapper mapper;
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(Mapper mapper, EmployeeRepository employeeRepository) {
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
    }

    public Collection<EmployeeResponseDto> findAll() {
        return this.employeeRepository.findAll().stream()
                .map(employee -> this.mapper.map(employee, EmployeeResponseDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto) {
        Employee employee = this.mapper.map(employeeRequestDto, Employee.class);
        employee = this.employeeRepository.save(employee);
        return this.mapper.map(employee, EmployeeResponseDto.class);
    }

    public EmployeeResponseDto findById(long userId) {
        Employee employee = this.employeeRepository.findById(userId).orElseThrow(EmployeeNotFoundException::new);
        return this.mapper.map(employee, EmployeeResponseDto.class);
    }


    public EmployeeResponseDto delete(long userId) {
        Employee employee = this.employeeRepository.findById(userId).orElseThrow(EmployeeNotFoundException::new);
        this.employeeRepository.delete(employee);
        return this.mapper.map(employee, EmployeeResponseDto.class);
    }

}
