package es.codeurjc.monolith.dtos.requests;

import lombok.Data;

@Data
public class CustomerRequestDto {

    private Long id;
    private String name;
    private String status;

}
