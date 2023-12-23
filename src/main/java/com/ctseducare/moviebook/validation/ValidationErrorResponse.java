package com.ctseducare.moviebook.validation;

import java.util.Collection;

public class ValidationErrorResponse {

	private String message;
	private Collection<FieldError> errors;
	
	public ValidationErrorResponse(String message, Collection<FieldError> errors) {
		this.message = message;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Collection<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(Collection<FieldError> errors) {
		this.errors = errors;
	}

}
