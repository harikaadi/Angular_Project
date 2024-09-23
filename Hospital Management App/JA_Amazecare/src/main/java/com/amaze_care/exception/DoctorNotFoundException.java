package com.amaze_care.exception;

public class DoctorNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;
	
	public DoctorNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}

