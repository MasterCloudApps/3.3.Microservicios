package es.codeurjc.inventoryservice.stream.kafka;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import es.codeurjc.inventoryservice.model.events.AllocateRequest;
import es.codeurjc.inventoryservice.model.events.AllocateResult;
import es.codeurjc.inventoryservice.model.events.DeallocateRequest;
import es.codeurjc.inventoryservice.model.events.dto.OrderDto;
import es.codeurjc.inventoryservice.service.AllocationService;


@Component
@Transactional
public class InventoryStreamListener {
	
	private Logger log = LoggerFactory.getLogger(InventoryStreamListener.class);
	private final AllocationService allocationService;
	private final InventoryStreamService inventoryStreamService;
	
	@Autowired
	public InventoryStreamListener(AllocationService allocationService,InventoryStreamService inventoryStreamService) {
		this.allocationService = allocationService;
		this.inventoryStreamService = inventoryStreamService;
	}
	
	@StreamListener(InventoryStream.INPUT_ALLOCATE_ORDER)
	public void handleAllocateRequest(@Payload AllocateRequest allocateRequest) {
		final OrderDto orderDto =  allocateRequest.getOrder();
		final Boolean result = allocationService.allocateOrder(orderDto);
		final AllocateResult allocateResult = new AllocateResult.Builder().withIsValid(result).withOrderId(orderDto.getId()).withReason(Boolean.FALSE.equals(result) ? "SOLD_OUT":null).build();
		inventoryStreamService.sendAllocateResult(allocateResult);
	}
	
	@StreamListener(InventoryStream.INPUT_DEALLOCATE_ORDER)
    public void handleDeallocateRequest(@Payload DeallocateRequest deallocateRequest) {
		final OrderDto orderDto =  deallocateRequest.getOrder();
		allocationService.deallocateOrder(orderDto);
	}
}
