package com.muntu.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
	private String errorField;
	private String errorMessage;

	public static Error of(	String errorField, String errorMessage) {
		
		return new Error(errorField,errorMessage);
	}
}
