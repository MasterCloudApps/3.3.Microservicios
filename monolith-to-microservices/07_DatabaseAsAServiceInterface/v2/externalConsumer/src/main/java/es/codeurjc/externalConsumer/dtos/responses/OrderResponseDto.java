package es.codeurjc.externalConsumer.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;
    private String dateOrder;
    private String purchaser;
    private String address;
    private String totalPrice;

}
