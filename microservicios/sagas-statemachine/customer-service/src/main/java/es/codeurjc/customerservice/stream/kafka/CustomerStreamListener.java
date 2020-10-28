package es.codeurjc.customerservice.stream.kafka;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import es.codeurjc.customerservice.domain.CustomerCreditLimitExceededException;
import es.codeurjc.customerservice.domain.CustomerNotFoundException;
import es.codeurjc.customerservice.model.events.CreditRequest;
import es.codeurjc.customerservice.model.events.CreditResult;
import es.codeurjc.customerservice.model.events.dto.OrderDetailsDto;
import es.codeurjc.customerservice.service.CustomerService;

@Component
@Transactional
public class CustomerStreamListener {
	
	private Logger log = LoggerFactory.getLogger(CustomerStreamListener.class);

	private final CustomerService customerService;
	private final CustomerStreamService customerStreamService;

	public CustomerStreamListener(CustomerService customerService,CustomerStreamService customerStreamService) {
		this.customerService = customerService;
		this.customerStreamService = customerStreamService;
	}

	@StreamListener(CustomerStream.INPUT_CREDIT_ORDER)
	public void handleCreditRequest(@Payload CreditRequest creditRequest) {
		log.info("Sending creditRequest {}", creditRequest);
		final OrderDetailsDto orderDetailsDto = creditRequest.getOrderDetailsDto();
		CreditResult creditResult = new CreditResult.Builder().withIsValid(Boolean.TRUE).withOrderId(orderDetailsDto.getOrderId()).build();
		try {
			customerService.reserveCredit(orderDetailsDto.getCustomerId(), orderDetailsDto.getOrderId(),orderDetailsDto.getOrderTotal());
			
		} catch (CustomerNotFoundException e) {
			creditResult = new CreditResult.Builder().withIsValid(Boolean.FALSE).withOrderId(orderDetailsDto.getOrderId()).withRejectionReason("UNKNOWN_CUSTOMER").build();
		} catch (CustomerCreditLimitExceededException e) {
			creditResult = new CreditResult.Builder().withIsValid(Boolean.FALSE).withOrderId(orderDetailsDto.getOrderId()).withRejectionReason("INSUFFICIENT_CREDIT").build();
		}
		
		customerStreamService.sendCreditResponse(creditResult);
	}
}
