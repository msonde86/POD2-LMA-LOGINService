package com.scb.pod2.login.service;

import java.util.Map;

import com.scb.pod2.login.dto.UserDTO;
import com.scb.pod2.login.model.User;

public interface UserLoginService {
	public Map<String, String> checkUserLogin(UserDTO user);
	public Map<String, String> generateJwtToken(User user);
	public String encodePassword(String password);
}
