package com.scb.pod2.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.pod2.login.dao.UserLoginResource;
import com.scb.pod2.login.model.User;
import com.scb.pod2.login.util.PasswordDataEncryption;

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
	public User checkUserLogin(User user) {
		User loginUser = loginResource.findUser(user.getEmailId(), encodePassword(user.getPassword()));
		return loginUser;
	}
	

	public String encodePassword(String password) {
		//return Base64.getUrlEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
		return encryption.encryptPassword(password, "scbpod2");
	}

}
