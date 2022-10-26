package com.softserve.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ForgotPasswordProcessException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ForgotPasswordProcessException(String message) {
		super(message);
		this.message = message;
	}

}
