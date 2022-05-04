package es.codeurjc.externalConsumer.dtos.responses;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDto {

    private Long id;
    private String dateOrder;
    private String purchaser;
    private String address;
    private String totalPrice;

}
