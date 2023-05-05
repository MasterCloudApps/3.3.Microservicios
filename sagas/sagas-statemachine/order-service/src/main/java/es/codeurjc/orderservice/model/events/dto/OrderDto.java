package es.codeurjc.orderservice.model.events.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.codeurjc.orderservice.types.OrderStatusEnum;

public class OrderDto {
	
    @JsonProperty("id")
    private UUID id;
    
    @JsonProperty("productId")
    private String productId;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonIgnore
    private OrderStatusEnum state;
    
    public UUID getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public OrderStatusEnum getState() {
        return state;
    }

    public static final class Builder {

        private final OrderDto object;

        public Builder() {
            object = new OrderDto();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }
        
        public Builder withProductId(String value) {
            object.productId = value;
            return this;
        }
        
        public Builder withQuantity(Integer value) {
            object.quantity = value;
            return this;
        }

        public Builder withState(OrderStatusEnum value) {
            object.state = value;
            return this;
        }

        public OrderDto build() {
            return object;
        }

    }
}
