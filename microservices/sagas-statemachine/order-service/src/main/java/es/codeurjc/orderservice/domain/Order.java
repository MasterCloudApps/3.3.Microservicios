package es.codeurjc.orderservice.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.codeurjc.orderservice.common.OrderDetails;
import es.codeurjc.orderservice.types.OrderStatusEnum;
import es.codeurjc.orderservice.types.RejectionReasonEnum;

@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

    private String name;
    
    private String reference;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
	private OrderStatusEnum state;

	@Embedded
	private OrderDetails orderDetails;
	
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
	private RejectionReasonEnum rejectionReason;
	
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

	public Order() {
	}

	public Order(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
		this.state = OrderStatusEnum.NEW;
	}

    public UUID getId() {
        return id;
    }

	public void setId(UUID id) {
		this.id = id;
	}

	public OrderStatusEnum getState() {
        return state;
    }

    public void setState(OrderStatusEnum state) {
		this.state = state;
	}


	public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public String getReference() {
		return reference;
	}
    
    public RejectionReasonEnum getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(RejectionReasonEnum rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

    
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}



	public static final class Builder {

        private final Order object;

        public Builder() {
            object = new Order();
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
        
        public Builder withQuantity(Integer value) {
            object.quantity = value;
            return this;
        }

        public Builder withState(OrderStatusEnum value) {
            object.state = value;
            return this;
        }
        
        public Builder withOrderDetails(OrderDetails value) {
            object.orderDetails = value;
            return this;
        }

        public Builder withRejectionReason(RejectionReasonEnum value) {
            object.rejectionReason = value;
            return this;
        }

        public Order build() {
            return object;
        }

    }

}
