package es.codeurjc.customerservice.model.events.dto;

import java.util.UUID;

import es.codeurjc.customerservice.common.Money;

public class OrderDetailsDto {
	
	private UUID orderId;
	private UUID customerId;
	private Money orderTotal;
	
    public UUID getOrderId() {
		return orderId;
	}

	public UUID getCustomerId() {
        return customerId;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public static final class Builder {

        private final OrderDetailsDto object;

        public Builder() {
            object = new OrderDetailsDto();
        }
        
        public Builder withOrderId(UUID value) {
            object.orderId = value;
            return this;
        }
        
        public Builder withCustomerId(UUID value) {
            object.customerId = value;
            return this;
        }

        public Builder withOrderTotal(Money value) {
            object.orderTotal = value;
            return this;
        }

        public OrderDetailsDto build() {
            return object;
        }

    }
}
