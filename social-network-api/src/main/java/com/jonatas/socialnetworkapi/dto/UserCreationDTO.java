package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.User;

public class UserCreationDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	private String id;
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

	public UserCreationDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.invitation = user.getInvitation().getValue() != null ? user.getInvitation().getValue() : null;
		this.password = user.getPassword();
	}
	
	//getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "UserCreationDTO [id=" + id + ", name=" + name + ", email=" + email + ", invitation=" + invitation
				+ ", password=" + password + "]";
	}

	

	
}
