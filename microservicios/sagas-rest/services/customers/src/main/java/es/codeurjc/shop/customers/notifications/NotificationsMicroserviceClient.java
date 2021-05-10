package es.codeurjc.shop.customers.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.codeurjc.shop.customers.dto.NotificationDto;

@Component
public class NotificationsMicroserviceClient {

	@Value(value = "${notifications.url}")
	private String notificationsMsBaseUri;

	@Autowired
	private RestTemplate restTemplate;

	public void sendNotification(final NotificationDto notification) {
		HttpEntity<NotificationDto> requestEntity = new HttpEntity<>(notification, getJsonHeaders());
		restTemplate.exchange(notificationsMsBaseUri + "notifications/", HttpMethod.POST, requestEntity, String.class);
	}

	private HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
