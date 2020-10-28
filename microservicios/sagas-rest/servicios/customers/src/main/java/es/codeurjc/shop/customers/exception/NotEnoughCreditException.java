package es.codeurjc.shop.customers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Not enough credit")
public class NotEnoughCreditException extends RuntimeException {

	private static final long serialVersionUID = -2891175795221859646L;

}