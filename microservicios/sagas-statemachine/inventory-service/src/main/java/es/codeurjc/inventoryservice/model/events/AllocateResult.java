package es.codeurjc.inventoryservice.model.events;

import java.util.UUID;

public class AllocateResult {
	
    private UUID orderId;
    private Boolean isValid;
    private String reason;
    
    public UUID getOrderId() {
        return orderId;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public String getReason() {
		return reason;
	}

	public static final class Builder {

        private final AllocateResult object;

        public Builder() {
            object = new AllocateResult();
        }

        public Builder withOrderId(UUID value) {
            object.orderId = value;
            return this;
        }

        public Builder withIsValid(Boolean value) {
            object.isValid = value;
            return this;
        }

        public Builder withReason(String value) {
            object.reason = value;
            return this;
        }
        
        public AllocateResult build() {
            return object;
        }

    }
}
