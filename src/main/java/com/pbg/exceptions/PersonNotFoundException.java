package com.pbg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Persona no encontrada") //404
public class PersonNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2815035829628231710L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}
	public PersonNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public PersonNotFoundException() {
		super();
	}
}
