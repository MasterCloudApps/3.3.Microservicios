package es.codeurjc.customerservice.model.events;

import es.codeurjc.customerservice.model.events.dto.OrderDetailsDto;

public class CreditRequest {
	
	private OrderDetailsDto orderDetailsDto;
	
    public OrderDetailsDto getOrderDetailsDto() {
        return orderDetailsDto;
    }

    public static final class Builder {

        private final CreditRequest object;

        public Builder() {
            object = new CreditRequest();
        }

        public Builder withOrderDetailsDto(OrderDetailsDto value) {
            object.orderDetailsDto = value;
            return this;
        }

        public CreditRequest build() {
            return object;
        }

    }

}
