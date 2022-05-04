package es.codeurjc.loyalty.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "Loyalty number is mandatory")
    private String loyaltyCardNumber;

}
