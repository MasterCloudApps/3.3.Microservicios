package es.codeurjc.inventoryservice.dto;

import java.util.UUID;

public class CreateInventoryResponse {

	private UUID inventoryId;

	
	public CreateInventoryResponse() {
	}

	public CreateInventoryResponse(UUID inventoryId) {
		this.inventoryId = inventoryId;
	}

	public UUID getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(UUID inventoryId) {
		this.inventoryId = inventoryId;
	}

}
