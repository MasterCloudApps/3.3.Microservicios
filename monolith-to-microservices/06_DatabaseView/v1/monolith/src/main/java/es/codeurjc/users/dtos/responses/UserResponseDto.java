package es.codeurjc.users.dtos.responses;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String address;
    private String password;
    private String loyaltyCardNumber;

}
