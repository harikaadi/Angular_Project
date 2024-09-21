package com.amaze_care.exception;

public class InvalidSpecializationException extends Exception{
	
	private String message;

	public InvalidSpecializationException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
