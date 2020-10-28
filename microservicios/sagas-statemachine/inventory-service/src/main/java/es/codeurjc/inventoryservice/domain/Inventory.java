package es.codeurjc.inventoryservice.domain;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "inventory")
@Access(AccessType.FIELD)
public class Inventory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;
    
    @Column(unique = true)
    private String name;
    
    @Column(unique = true)
    private String reference;
	
    private Integer stockQuantity = 0;
    
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

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public static final class Builder {

        private final Inventory object;

        public Builder() {
            object = new Inventory();
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

        public Inventory build() {
            return object;
        }

    }
}
