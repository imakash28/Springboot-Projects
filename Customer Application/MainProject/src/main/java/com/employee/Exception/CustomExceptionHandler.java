package com.employee.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex) {
		String errorMessage = ex.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}
}
