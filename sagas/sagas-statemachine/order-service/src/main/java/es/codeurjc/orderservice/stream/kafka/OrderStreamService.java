package es.codeurjc.orderservice.stream.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import es.codeurjc.orderservice.model.events.AllocateRequest;
import es.codeurjc.orderservice.model.events.CreditRequest;
import es.codeurjc.orderservice.model.events.DeallocateRequest;
import es.codeurjc.orderservice.model.events.dto.OrderDetailsDto;
import es.codeurjc.orderservice.model.events.dto.OrderDto;

@Service
public class OrderStreamService {

	private Logger log = LoggerFactory.getLogger(OrderStreamService.class);
	
	private final OrderStream orderStream;
	
	@Autowired
	public OrderStreamService(OrderStream orderStream) {
		this.orderStream = orderStream;
	}
	
	public void sendAllocateRequest(final OrderDto orderDto) {
		log.info("Sending orderDto {}", orderDto);
        final AllocateRequest allocateRequest = new AllocateRequest.Builder()
        		                                                          .withOrder(orderDto)
        		                                                          .build();
        MessageChannel messageChannel = orderStream.outboundAllocateOrder();
        messageChannel.send(MessageBuilder
                .withPayload(allocateRequest)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}

	public void sendDeallocateRequest(final OrderDto orderDto) {
		log.info("Sending orderDto {}", orderDto);
        final DeallocateRequest deallocateRequest = new DeallocateRequest.Builder()
        		                                                          .withOrder(orderDto)
        		                                                          .build();
        MessageChannel messageChannel = orderStream.outboundDeallocateOrder();
        messageChannel.send(MessageBuilder
                .withPayload(deallocateRequest)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}

	
	public void sendCreditRequest(final OrderDetailsDto orderDetailsDto ) {
		log.info("Sending orderDetailsDto {}", orderDetailsDto);
		
		final CreditRequest creditRequest = new CreditRequest.Builder()
				                                             .withOrderDetailsDto(orderDetailsDto)
				                                             .build();
		
		MessageChannel messageChannel = orderStream.outboundCreditOrder();
        messageChannel.send(MessageBuilder
                .withPayload(creditRequest)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}
}
