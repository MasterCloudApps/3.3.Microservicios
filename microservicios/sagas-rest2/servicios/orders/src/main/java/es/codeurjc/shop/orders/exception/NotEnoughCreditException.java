package es.codeurjc.shop.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Not enough credit")
public class NotEnoughCreditException extends RuntimeException {

	private static final long serialVersionUID = 1866666142773304159L;
	
	public NotEnoughCreditException() {
		super();
	}

	public NotEnoughCreditException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotEnoughCreditException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughCreditException(String message) {
		super(message);
	}

	public NotEnoughCreditException(Throwable cause) {
		super(cause);
	}
}