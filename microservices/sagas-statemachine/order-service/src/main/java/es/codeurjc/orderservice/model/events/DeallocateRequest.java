package es.codeurjc.orderservice.model.events;

import es.codeurjc.orderservice.model.events.dto.OrderDto;


public class DeallocateRequest {

    private OrderDto order;
    
    public OrderDto getOrder() {
        return order;
    }

    public static final class Builder {

        private final DeallocateRequest object;

        public Builder() {
            object = new DeallocateRequest();
        }

        public Builder withOrder(OrderDto value) {
            object.order = value;
            return this;
        }

        public DeallocateRequest build() {
            return object;
        }

    }
}
