package es.codeurjc.orderservice.stream.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderStream {

	public String OUTPUT_ALLOCATE_ORDER = "allocate-order-request";
    public String INPUT_ALLOCATE_ORDER = "allocate-order-response";
	public String OUTPUT_CREDIT_ORDER = "credit-order-request";
    public String INPUT_CREDIT_ORDER = "credit-order-response";
    public String OUTPUT_DEALLOCATE_ORDER = "deallocate-order-request";
    
    @Input(INPUT_ALLOCATE_ORDER)
    SubscribableChannel inboundAllocateOrder();

    @Output(OUTPUT_ALLOCATE_ORDER)
    MessageChannel outboundAllocateOrder();
    
    @Input(INPUT_CREDIT_ORDER)
    SubscribableChannel inboundCreditOrder();

    @Output(OUTPUT_CREDIT_ORDER)
    MessageChannel outboundCreditOrder();
    
    @Output(OUTPUT_DEALLOCATE_ORDER)
    MessageChannel outboundDeallocateOrder();

}
