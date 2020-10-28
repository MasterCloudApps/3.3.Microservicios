package es.codeurjc.shop.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2700825386063335911L;

}