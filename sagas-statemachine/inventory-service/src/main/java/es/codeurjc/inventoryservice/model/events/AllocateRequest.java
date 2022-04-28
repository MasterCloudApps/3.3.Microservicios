package es.codeurjc.inventoryservice.model.events;

import es.codeurjc.inventoryservice.model.events.dto.OrderDto;


public class AllocateRequest {

    private OrderDto order;
    
    public OrderDto getOrder() {
        return order;
    }

    public static final class Builder {

        private final AllocateRequest object;

        public Builder() {
            object = new AllocateRequest();
        }

        public Builder withOrder(OrderDto value) {
            object.order = value;
            return this;
        }

        public AllocateRequest build() {
            return object;
        }

    }
}
