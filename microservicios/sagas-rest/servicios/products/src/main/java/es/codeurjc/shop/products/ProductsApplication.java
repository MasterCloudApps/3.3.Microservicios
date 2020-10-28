package es.codeurjc.shop.products;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import es.codeurjc.shop.products.entity.Product;
import es.codeurjc.shop.products.repository.ProductRepository;

@SpringBootApplication
public class ProductsApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Load some data in db
		final Product chair = new Product("Chair", 12);
		productRepository.save(chair);
	}

	@Bean
	RestTemplate restTemplate() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		return createRestTemplate(httpClient);
	}

	private RestTemplate createRestTemplate(final CloseableHttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

}
