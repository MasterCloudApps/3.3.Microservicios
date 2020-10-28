package es.codeurjc.shop.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Not enough stock")
public class NotEnoughStockException extends RuntimeException {

	private static final long serialVersionUID = -8931101191407160288L;

	public NotEnoughStockException() {
		super();
	}

	public NotEnoughStockException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotEnoughStockException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotEnoughStockException(String message) {
		super(message);
	}

	public NotEnoughStockException(Throwable cause) {
		super(cause);
	}

}