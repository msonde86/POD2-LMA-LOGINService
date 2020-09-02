package com.scb.pod2.login.service;

import com.scb.pod2.login.dto.UserDTO;
import com.scb.pod2.login.model.User;

public interface UserLoginService {
	public User checkUserLogin(User user);
	public UserDTO setUserDTO(User user);
}
