package com.scb.pod2.login.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.pod2.login.dao.UserLoginResource;
import com.scb.pod2.login.dto.UserDTO;
import com.scb.pod2.login.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginResource loginResource;
	
	/*
	 * Encode the password using base 64.
	 * Check if the given credentials is present in DB
	 * @param UserDTO object from controller
	 */
	@Override
	public Map<String, String> checkUserLogin(UserDTO user) {
		User loginUser = loginResource.findUser(user.getEmailId(), encodePassword(user.getPassword()));
		Map<String,String> tokenMap = null;
		if(loginUser != null) {
			tokenMap = generateJwtToken(loginUser);
		}
		return tokenMap;
	}
	/*
	 * Generate JWT token using the user emailID
	 * @param User object fetched from repository
	 */
	public Map<String, String> generateJwtToken(User user) {
		String token = Jwts.builder().setSubject(user.getEmailId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256,"scbpod2").compact();
		Map<String,String> jwtMap = new HashMap<>();
		jwtMap.put("token", token);
		return jwtMap;
	}
	
	/*
	 * Encode the password received from client end in controller
	 * @param password from request body of UserDTO object
	 */
	public String encodePassword(String password) {
		return Base64.getUrlEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
	}

}
