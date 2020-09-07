package com.scb.pod2.login.service;

import java.util.Map;

import com.scb.pod2.login.model.User;

public interface UserLoginService {
	public Map<String, String> checkUserLogin(User user);
}
