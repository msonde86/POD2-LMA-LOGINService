package com.scb.pod2.login.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserLoginExceptionHandler extends ResponseEntityExceptionHandler{
	
	/*
	 * Exception handler method for other common errors
	 */
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResonse=new ExceptionResponse(new Date(),"Internal Error Occurred", 
        		"Error exception response: Internal Server Error");
        return new ResponseEntity<Object>(exceptionResonse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
	/*
	 * Exception handler method when the login details is not present in DB
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> loginFailedException(Exception ex, WebRequest request) throws Exception{
		ExceptionResponse exceptionResonse=new ExceptionResponse(new Date(), ex.getMessage(), 
        		"Error exception response: Unknown user");
		return new ResponseEntity<Object>(exceptionResonse,HttpStatus.UNAUTHORIZED);
	}
	
}
