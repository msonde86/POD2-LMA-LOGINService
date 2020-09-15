package com.scb.pod2.login.dto;

public class UserDTO {
	private int userId;
	private String emailId;
	private String password;
	
	public UserDTO() {}
	public UserDTO(int userId,String emailId, String password) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
