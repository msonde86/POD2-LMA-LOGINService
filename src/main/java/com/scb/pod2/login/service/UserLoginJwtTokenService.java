package com.scb.pod2.login.service;

import java.util.Map;

import com.scb.pod2.login.model.User;

public interface UserLoginJwtTokenService {
	public Map<String, String> generateJwtToken(User user);
}
