package com.revature.charityapp.exception;

public class ValidatorException extends Exception {
	public ValidatorException(String message) {
		super(message);
	}

	public ValidatorException(String message, Throwable t) {
		super(message, t);
	}

}
