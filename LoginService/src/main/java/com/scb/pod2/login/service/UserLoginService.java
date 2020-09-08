package com.scb.pod2.login.service;

import java.util.Map;

import com.scb.pod2.login.dto.UserDTO;

public interface UserLoginService {
	public Map<String, String> checkUserLogin(UserDTO user);
}
