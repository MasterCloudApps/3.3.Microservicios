package es.codeurjc.employees.dtos.requests;

import lombok.Data;

@Data
public class EmployeeRequestDto {

    private Long id;
    private String name;
    private String lastName;
    private String birthDate;
    private String document;


}
