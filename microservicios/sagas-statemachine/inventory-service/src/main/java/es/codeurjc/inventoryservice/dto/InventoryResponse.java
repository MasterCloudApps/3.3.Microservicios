package es.codeurjc.inventoryservice.dto;

import java.util.UUID;

public class InventoryResponse {
	
	private UUID id;
    private String name;
    private String reference;
    private Integer stockQuantity;
    
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReference() {
        return reference;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public static final class Builder {

        private final InventoryResponse object;

        public Builder() {
            object = new InventoryResponse();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withName(String value) {
            object.name = value;
            return this;
        }

        public Builder withReference(String value) {
            object.reference = value;
            return this;
        }

        public Builder withStockQuantity(Integer value) {
            object.stockQuantity = value;
            return this;
        }

        public InventoryResponse build() {
            return object;
        }

    }
    
    
}
