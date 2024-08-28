package org.ebay.demo.exceptions;

import org.ebay.demo.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadParameterException.class)
	public ResponseEntity<Response<String>> handleBadParameterException(BadParameterException ex) {
		Response<String> response = new Response<>(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CalculationException.class)
	public ResponseEntity<Response<String>> handleCalculationException(CalculationException ex) {
		Response<String> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<String>> handleGeneralException(Exception ex) {
		Response<String> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "An unexpected error occurred", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
