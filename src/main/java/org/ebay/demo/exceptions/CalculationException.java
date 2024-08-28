package org.ebay.demo.exceptions;

import lombok.Data;

@Data
public class CalculationException extends RuntimeException{
	public CalculationException(String message) {
		super(message);
	}
	public CalculationException(String message, Throwable cause) {
		super(message, cause);
	}
	public CalculationException(Throwable cause) {
		super(cause);
	}
}
