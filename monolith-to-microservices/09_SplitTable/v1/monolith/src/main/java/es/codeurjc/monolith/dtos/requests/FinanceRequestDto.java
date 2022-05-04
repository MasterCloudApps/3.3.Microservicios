package es.codeurjc.monolith.dtos.requests;

import lombok.Data;

@Data
public class FinanceRequestDto {

    private Long id;
    private String name;
    private String status;

}
