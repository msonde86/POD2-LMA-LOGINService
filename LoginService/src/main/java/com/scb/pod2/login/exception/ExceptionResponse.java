package com.scb.pod2.login.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
	private Date timeStamp;
	private String message;
	private String description;
	
	
	
}
