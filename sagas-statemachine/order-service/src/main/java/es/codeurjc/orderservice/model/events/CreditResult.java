package es.codeurjc.orderservice.model.events;

import java.util.UUID;

public class CreditResult {
	
    private UUID orderId;
    private Boolean isValid;
    private String rejectionReason;
    
    public UUID getOrderId() {
        return orderId;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public static final class Builder {

        private final CreditResult object;

        public Builder() {
            object = new CreditResult();
        }

        public Builder withOrderId(UUID value) {
            object.orderId = value;
            return this;
        }

        public Builder withIsValid(Boolean value) {
            object.isValid = value;
            return this;
        }

        public Builder withRejectionReason(String value) {
            object.rejectionReason = value;
            return this;
        }

        public CreditResult build() {
            return object;
        }

    }
}
