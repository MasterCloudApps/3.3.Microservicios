package es.codeurjc.employees.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employ not found")
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4570803624977277090L;
}
