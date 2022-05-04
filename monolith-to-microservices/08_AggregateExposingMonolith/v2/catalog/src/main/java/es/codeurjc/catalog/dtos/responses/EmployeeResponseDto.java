package es.codeurjc.catalog.dtos.responses;

import lombok.Data;

@Data
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String lastName;
    private String birthDate;
    private String document;

}
