package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

public class UserAuthDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	private String email;
	private String password;
	
	//builders
	
	public UserAuthDTO() {
		super();
	}

	public UserAuthDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	//getters and setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
