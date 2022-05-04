package es.codeurjc.catalog.dtos.requests;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeeRequestDto {

    @NotBlank(message = "Employ Id number is mandatory")
    private String name;

}
