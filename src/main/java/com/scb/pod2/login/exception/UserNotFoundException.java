package com.scb.pod2.login.exception;

public class UserNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Throw this exception when user is not present in DB
	 */
	public UserNotFoundException(String message) {
		super(message);
	}

}
