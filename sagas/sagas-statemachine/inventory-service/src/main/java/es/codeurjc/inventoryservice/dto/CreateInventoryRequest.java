package es.codeurjc.inventoryservice.dto;

public class CreateInventoryRequest {
	
    private String name;
    private String reference;
    private Integer stockQuantity;
    
    public CreateInventoryRequest() {
    	
    }
    
	public CreateInventoryRequest(String name, String reference, Integer stockQuantity) {
		this.name = name;
		this.reference = reference;
		this.stockQuantity = stockQuantity;
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
