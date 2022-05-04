package es.codeurjc.customer.dtos.responses;

import lombok.Data;

@Data
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String status;

}
