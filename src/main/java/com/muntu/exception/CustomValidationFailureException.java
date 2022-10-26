package com.muntu.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomValidationFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String field;

	public CustomValidationFailureException(String message) {
		super(message);
	}

	public CustomValidationFailureException(String field, String message) {
		super(message);
		this.field = field;
	}

}