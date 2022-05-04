package es.codeurjc.inventoryservice.stream.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface InventoryStream {

	public String INPUT_ALLOCATE_ORDER = "allocate-order-request";
    public String OUTPUT_ALLOCATE_ORDER = "allocate-order-response";
    public String INPUT_DEALLOCATE_ORDER = "deallocate-order-request";
    
    @Input(INPUT_ALLOCATE_ORDER)
    SubscribableChannel inboundAllocateOrder();

    @Output(OUTPUT_ALLOCATE_ORDER)
    MessageChannel outboundAllocateOrder();
    
    @Output(INPUT_DEALLOCATE_ORDER)
    SubscribableChannel inboundDeallocateOrder();
}
