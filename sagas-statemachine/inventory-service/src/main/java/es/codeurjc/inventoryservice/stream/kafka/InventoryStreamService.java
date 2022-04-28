package es.codeurjc.inventoryservice.stream.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import es.codeurjc.inventoryservice.model.events.AllocateResult;

@Service
public class InventoryStreamService {
	
	private Logger log = LoggerFactory.getLogger(InventoryStreamService.class);
	
	private final InventoryStream inventoryStream;
	
	@Autowired
	public InventoryStreamService(InventoryStream inventoryStream) {
		this.inventoryStream = inventoryStream;
	}
	
	public void sendAllocateResult(final AllocateResult allocateResult) {
		log.info("Sending allocateResult {}", allocateResult);
		MessageChannel messageChannel = inventoryStream.outboundAllocateOrder();
        messageChannel.send(MessageBuilder
                .withPayload(allocateResult)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}
}
