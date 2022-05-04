package es.codeurjc.orders.dtos.requests;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRequestDto {

    private Long id;
    private String dateOrder;
    private String purchaser;
    private String address;
    private String totalPrice;

}
