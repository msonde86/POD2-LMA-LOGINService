package com.scb.pod2.login.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
