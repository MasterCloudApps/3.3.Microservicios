package es.codeurjc.monolith.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order not found")
public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4570803624977277090L;
}
