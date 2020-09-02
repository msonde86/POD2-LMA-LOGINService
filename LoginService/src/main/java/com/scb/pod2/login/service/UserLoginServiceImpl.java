package com.scb.pod2.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.pod2.login.dao.UserLoginResource;
import com.scb.pod2.login.dto.UserDTO;
import com.scb.pod2.login.model.User;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginResource loginResource;
	
	@Override
	public User checkUserLogin(User user) {
		User loginUser = loginResource.findUser(user.getEmailId(), user.getPassword());
		return loginUser;
	}

	@Override
	public UserDTO setUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setUserName(user.getEmailId());
		return userDTO;
	}

}
