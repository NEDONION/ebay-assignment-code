package org.ebay.demo.exceptions;

import org.ebay.demo.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response handleBadParameterException(BadParameterException ex) {
		return new Response(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null);
	}

	@ExceptionHandler(CalculationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Response handleCalculationException(CalculationException ex) {
		return new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), null);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Response handleGeneralException(Exception ex) {
		return new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), null);
	}
}
