package org.ebay.demo.exceptions;

import lombok.Data;

@Data
public class BadParameterException extends RuntimeException {
	public BadParameterException(String message) {
		super(message);
	}
	public BadParameterException(String message, Throwable cause) {
		super(message, cause);
	}
	public BadParameterException(Throwable cause) {
		super(cause);
	}
}
