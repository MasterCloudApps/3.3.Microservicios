package es.codeurjc.inventoryservice.web;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.codeurjc.inventoryservice.dto.CreateInventoryRequest;
import es.codeurjc.inventoryservice.dto.CreateInventoryResponse;
import es.codeurjc.inventoryservice.dto.InventoryResponse;
import es.codeurjc.inventoryservice.dto.UpdateInventoryRequest;
import es.codeurjc.inventoryservice.dto.UpdateInventoryResponse;
import es.codeurjc.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

	private InventoryService inventoryService;
	
	@Autowired
	public ProductController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

    @GetMapping("products")
    public ResponseEntity<List<InventoryResponse>> getProducts() {
        return new ResponseEntity<>(inventoryService.getInventories(), HttpStatus.OK);
    }

    @GetMapping("products/{productId}")
    public ResponseEntity<InventoryResponse> getProduct(@PathVariable(value = "productId") UUID productId) {
        return new ResponseEntity<>(inventoryService.getInventory(productId), HttpStatus.OK);
    }	
    
	@PostMapping("products")
	public ResponseEntity<CreateInventoryResponse> createProduct(@RequestBody CreateInventoryRequest createInventoryRequest) {
		return new ResponseEntity<>(inventoryService.createInventory(createInventoryRequest),HttpStatus.CREATED);
	}
	
	@PutMapping("products")
	public ResponseEntity<UpdateInventoryResponse> updateProduct(@RequestBody UpdateInventoryRequest updateInventoryRequest) {
		final UpdateInventoryResponse updateInventoryResponse = inventoryService
				.updateInventory(updateInventoryRequest);
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/products/{productId}")
				.build().expand(updateInventoryResponse.getInventoryId()).toUri();
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(updateInventoryResponse, headers, HttpStatus.CREATED);
	}
}
