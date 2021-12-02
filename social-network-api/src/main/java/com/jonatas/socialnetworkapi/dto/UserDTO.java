package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.User;

public class UserDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String password;
	private String invitation;
	
	public UserDTO() {
	}

	public UserDTO(String name, String email, String password, String invitation) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.invitation = invitation;
	}
	
	public UserDTO(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInvitation() {
		return invitation;
	}

	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}
	
	
}
