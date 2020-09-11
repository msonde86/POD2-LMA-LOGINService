package com.scb.pod2.login.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.scb.pod2.login.dto.UserDTO;
import com.scb.pod2.login.exception.FallbackException;
import com.scb.pod2.login.exception.UserNotFoundException;
import com.scb.pod2.login.service.UserLoginService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class UserLoginController {
	private Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	@Autowired
	private UserLoginService loginService;
	/*
	 * Check the email id and password is present in DB
	 * @param UserDTO object with emailID and password from client
	 * If present then generate JWT token and return OK
	 * If not then throw custom exception and return UNAUTHORIZED
	 * return ResponseEntity<Map<String, String>>
	 */
	@PostMapping("/authenticate")
	@HystrixCommand(fallbackMethod = "loginUserFallback")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserDTO user){
		ResponseEntity<Map<String, String>> responseEntity;
		Map<String, String> jwtToken = loginService.checkUserLogin(user);
		if(jwtToken != null && jwtToken.size()>0) {
			responseEntity = new ResponseEntity<>(jwtToken,HttpStatus.OK);
			logger.info("Login successfull");
		}else {
			logger.error("Login failed");
			throw new UserNotFoundException("Login failed");
		}
		return responseEntity;
	}
	/*
	 * Fallback method for loginuser method
	 * @param UserDTO object which we recive from client request
	 * @return FallbackException with 503 status code
	 */
	public ResponseEntity<Map<String, String>> loginUserFallback(UserDTO user){
		logger.error("Falback method of loginUser method executed.");
		throw new FallbackException("Oops! Something went wrong.");
	}
	

}
