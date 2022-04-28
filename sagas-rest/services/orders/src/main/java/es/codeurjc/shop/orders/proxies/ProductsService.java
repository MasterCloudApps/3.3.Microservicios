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

import es.codeurjc.shop.orders.dto.ProductDto;
import es.codeurjc.shop.orders.exception.NotEnoughStockException;

@Component
public class ProductsService {

	@Autowired
	private RestTemplate restTemplate;

	@Value(value = "${products.url}")
	private String productsBaseUrl;

	public ProductDto fetchProduct(long productId) {
		return restTemplate.getForObject(productsBaseUrl + "products/" + productId, ProductDto.class);
	}

	public void modifyProductStock(final long productId, final String operation, final int productQuantity)
			throws RestClientException, JsonProcessingException {

		final Map<String, String> stockUpdate = new HashMap<>();
		stockUpdate.put("operation", operation);
		stockUpdate.put("amount", String.valueOf(productQuantity));
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(stockUpdate, getJsonHeaders());

		try {

			restTemplate.exchange(productsBaseUrl + "products/" + productId, HttpMethod.PATCH, requestEntity,
					ProductDto.class);

		} catch (final HttpClientErrorException e) {

			if (e.getStatusCode() == HttpStatus.BAD_REQUEST && e.getMessage().contains("NotEnoughStockException")) {
				throw new NotEnoughStockException(e);
			}
		}
	}

	private HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
