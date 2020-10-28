package es.codeurjc.shop.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Not enough stock")
public class NotEnoughStockException extends RuntimeException {

	private static final long serialVersionUID = -5745186539436130347L;

}