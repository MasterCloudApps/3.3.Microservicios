package es.codeurjc.users.dtos.requests;

import lombok.Data;

@Data
public class UserRequestDto {

    private Integer id;
    private String address;
    private String password;
    private String loyaltyCardNumber;


}
