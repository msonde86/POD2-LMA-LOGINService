package com.scb.pod2.login.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Token {
	@Id
	private String jwtToken;

	public Token() {

	}

	public Token(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
