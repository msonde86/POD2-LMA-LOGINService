package com.scb.pod2.login.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.pod2.login.exception.UserNotFoundException;
import com.scb.pod2.login.model.User;
import com.scb.pod2.login.service.UserLoginJwtTokenService;
import com.scb.pod2.login.service.UserLoginService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class UserLoginController {
	private Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	@Autowired
	private UserLoginService loginService;
	@Autowired
	private UserLoginJwtTokenService jwtTokenService;
	
	private ResponseEntity<?> responseEntity;
	
	/*
	 * Check the email id and password is present in DB
	 * If present then generate JWT token and return OK
	 * If not then throw custom exception and return UNAUTHORIZED
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		User userObj = loginService.checkUserLogin(user);
		Map<String,String> tokenMap = null;
		if(userObj != null) {
			tokenMap = jwtTokenService.generateJwtToken(user);
			responseEntity = new ResponseEntity<>(tokenMap,HttpStatus.OK);
			logger.info("Login successfull");
		}else {
			logger.error("Login failed");
			throw new UserNotFoundException("Login failed");
		}
		return responseEntity;
	}
	
	

}
