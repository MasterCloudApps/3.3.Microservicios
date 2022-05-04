package es.codeurjc.customerservice.stream.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomerStream {

	public String INPUT_CREDIT_ORDER = "credit-order-request";
	public String OUTPUT_CREDIT_ORDER = "credit-order-response";
 
    
    @Input(INPUT_CREDIT_ORDER)
    SubscribableChannel inboundCreditOrder();

    @Output(OUTPUT_CREDIT_ORDER)
    MessageChannel outboundCreditOrder();
}
