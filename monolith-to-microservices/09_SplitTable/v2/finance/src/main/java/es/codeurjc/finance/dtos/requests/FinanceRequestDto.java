package es.codeurjc.finance.dtos.requests;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class FinanceRequestDto {

    private Long id;
    private String name;
    private String status;

}
