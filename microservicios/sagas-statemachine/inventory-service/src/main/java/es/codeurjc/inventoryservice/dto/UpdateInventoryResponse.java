package es.codeurjc.inventoryservice.dto;

import java.util.UUID;

public class UpdateInventoryResponse {

	private UUID inventoryId;

	
	public UpdateInventoryResponse() {
	}

	public UpdateInventoryResponse(UUID inventoryId) {
		this.inventoryId = inventoryId;
	}

	public UUID getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(UUID inventoryId) {
		this.inventoryId = inventoryId;
	}

}
