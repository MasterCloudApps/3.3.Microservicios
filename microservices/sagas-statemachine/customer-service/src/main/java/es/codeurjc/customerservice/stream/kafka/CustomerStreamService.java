package es.codeurjc.customerservice.stream.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import es.codeurjc.customerservice.model.events.CreditResult;


@Service
public class CustomerStreamService {
	
	private Logger log = LoggerFactory.getLogger(CustomerStreamService.class);
	
	private final CustomerStream customerStream;
	
	@Autowired
	public CustomerStreamService(CustomerStream customerStream) {
		this.customerStream = customerStream;
	}
	
	public void sendCreditResponse(final CreditResult creditResult) {
		log.info("Sending creditResult {}", creditResult);
		MessageChannel messageChannel = customerStream.outboundCreditOrder();
        messageChannel.send(MessageBuilder
                .withPayload(creditResult)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}
}
