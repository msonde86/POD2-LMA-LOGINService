package com.scb.pod2.login.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.scb.pod2.login.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserLoginJwtTokenServiceImpl implements UserLoginJwtTokenService{

	@Override
	public Map<String, String> generateJwtToken(User user) {
		String token = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256,"scbpod2").compact();
		Map<String,String> jwtMap = new HashMap<>();
		jwtMap.put("token", token);
		return jwtMap;
	}
	
}
