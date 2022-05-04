package es.codeurjc.inventoryservice.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.inventoryservice.domain.Inventory;
import es.codeurjc.inventoryservice.model.events.dto.OrderDto;
import es.codeurjc.inventoryservice.repository.InventoryRepository;


@Service
@Transactional
public class AllocationService {

	private Logger log = LoggerFactory.getLogger(AllocationService.class);

	private final InventoryRepository inventoryRepository;

	@Autowired
	public AllocationService(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public Boolean allocateOrder(OrderDto orderDto) {
		log.debug("Allocating OrderId: " + orderDto.getId());
	    Optional<Inventory> optInventory = inventoryRepository.findByReference(orderDto.getReference());
	    if (optInventory.isPresent()) {
	    	Inventory inventory = optInventory.get();
	    	if (inventory.getStockQuantity() > orderDto.getQuantity() ) {
	    		inventory.setStockQuantity(inventory.getStockQuantity() - orderDto.getQuantity());
	    		Inventory savedInventory = inventoryRepository.save(inventory);
	    		log.debug("Saved Inventory inventory id: " + savedInventory.getId());
	    		return Boolean.TRUE;
	    	}
	    }
	    return Boolean.FALSE;
	}


	public void deallocateOrder(OrderDto orderDto) {
		    
		    Optional<Inventory> optInventory = inventoryRepository.findByReference(orderDto.getReference());
		    if (optInventory.isPresent()) {
		    	Inventory inventory = optInventory.get();
		    	inventory.setStockQuantity(inventory.getStockQuantity() + orderDto.getQuantity());
				Inventory savedInventory = inventoryRepository.save(inventory);
				log.debug("Saved Inventory inventory id: " + savedInventory.getId());
		    }
	}
}
