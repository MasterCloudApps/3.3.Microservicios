package es.codeurjc.customerservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class CustomerResponse {

	private UUID id;
	private String name;
	private BigDecimal creditLimit;
	
    public UUID getId() {
		return id;
	}

	public String getName() {
        return name;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public static final class Builder {

        private final CustomerResponse object;

        public Builder() {
            object = new CustomerResponse();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }
        
        public Builder withName(String value) {
            object.name = value;
            return this;
        }

        public Builder withCreditLimit(BigDecimal value) {
            object.creditLimit = value;
            return this;
        }

        public CustomerResponse build() {
            return object;
        }

    }
}
