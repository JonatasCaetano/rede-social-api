package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

public class UserCreationDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	private String name;
	private String email;
	private String invitation;
	private String password;

	//builders
	
	public UserCreationDTO() {
		super();
	}
	
	public UserCreationDTO(String name, String email, String invitation, String password) {
		super();
		this.name = name;
		this.email = email;
		this.invitation = invitation;
		this.password = password;
	}
	
	//getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getInvitation() {
		return invitation;
	}

	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	
}
