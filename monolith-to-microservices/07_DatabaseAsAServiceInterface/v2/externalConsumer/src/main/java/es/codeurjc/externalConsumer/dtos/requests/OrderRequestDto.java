package es.codeurjc.externalConsumer.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {

    private Long id;
    private String dateOrder;
    private String purchaser;
    private String address;
    private String totalPrice;

}
