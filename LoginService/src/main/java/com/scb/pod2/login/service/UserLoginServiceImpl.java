package com.scb.pod2.login.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.pod2.login.dao.UserLoginResource;
import com.scb.pod2.login.model.User;
import com.scb.pod2.login.util.PasswordDataEncryption;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginResource loginResource;
	@Autowired
	private PasswordDataEncryption encryption;
	/*
	 * Encode the password using base 64.
	 * Check if the given credentials is present in DB
	 */
	@Override
	public Map<String, String> checkUserLogin(User user) {
		User loginUser = loginResource.findUser(user.getEmailId(), encodePassword(user.getPassword()));
		Map<String,String> tokenMap = null;
		if(loginUser != null) {
			tokenMap = generateJwtToken(user);
		}
		return tokenMap;
	}
	
	public Map<String, String> generateJwtToken(User user) {
		String token = Jwts.builder().setSubject(user.getEmailId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256,"scbpod2").compact();
		Map<String,String> jwtMap = new HashMap<>();
		jwtMap.put("token", token);
		return jwtMap;
	}
	

	public String encodePassword(String password) {
		//return Base64.getUrlEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
		return encryption.encryptPassword(password, "scbpod2");
	}

}
