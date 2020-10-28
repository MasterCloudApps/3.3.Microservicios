package es.codeurjc.inventoryservice.dto;

import java.util.UUID;

public class UpdateInventoryRequest {
	
	private UUID id;
    private String name;
    private String reference;
    private Integer stockQuantity;
    
    public UpdateInventoryRequest() {
    	
    }
    
	public UpdateInventoryRequest(UUID id, String name, String reference, Integer stockQuantity) {
		this.id = id;
		this.name = name;
		this.reference = reference;
		this.stockQuantity = stockQuantity;
	}

	public UpdateInventoryRequest(String name, String reference, Integer stockQuantity) {
		this.name = name;
		this.reference = reference;
		this.stockQuantity = stockQuantity;
	}
	
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
    
    
}
