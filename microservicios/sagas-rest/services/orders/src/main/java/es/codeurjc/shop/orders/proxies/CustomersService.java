package es.codeurjc.shop.orders.proxies;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.codeurjc.shop.orders.dto.CustomerDto;
import es.codeurjc.shop.orders.exception.NotEnoughCreditException;

@Component
public class CustomersService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value(value = "${customers.url}")
	private String customersBaseUrl;
	
	public CustomerDto fetchCustomer(long customerId) {
		return restTemplate.getForObject(customersBaseUrl + "customers/" + customerId, CustomerDto.class);
	}
	
	public void modifyCustomerCredit(final long customerId, final String operation, final double totalCost)
			throws RestClientException, JsonProcessingException {

		final Map<String, String> creditUpdate = new HashMap<>();
		creditUpdate.put("operation", operation);
		creditUpdate.put("amount", String.valueOf(totalCost));
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(creditUpdate, getJsonHeaders());

		try {
			restTemplate.exchange(customersBaseUrl + "customers/" + customerId, HttpMethod.PATCH, requestEntity,
				CustomerDto.class);
		
		} catch (final HttpClientErrorException e) {

			if (e.getStatusCode() == HttpStatus.BAD_REQUEST && e.getMessage().contains("NotEnoughCreditException")) {
				throw new NotEnoughCreditException(e);
			}
		}
	}
	
	private HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}


}
