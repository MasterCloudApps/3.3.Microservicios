package es.codeurjc.customerservice.model.events;

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

    public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
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
