package com.amaze_care.exception;

public class NoDoctorsAvailableException extends Exception{
	
	

	private String message;
	
	public NoDoctorsAvailableException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
