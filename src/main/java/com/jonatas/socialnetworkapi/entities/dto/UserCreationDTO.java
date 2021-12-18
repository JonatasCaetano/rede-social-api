package com.jonatas.socialnetworkapi.entities.dto;

public class UserCreationDTO {
	
	private String name;
	private String email;
	private String invitationValue;
	private String password;

	public UserCreationDTO() {
		super();
	}
	
	public UserCreationDTO(String name, String email, String invitation, String password) {
		super();
		this.name = name;
		this.email = email;
		this.invitationValue = invitation;
		this.password = password;
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

	public String getInvitationValue() {
		return invitationValue;
	}

	public void setInvitationValue(String invitationValue) {
		this.invitationValue = invitationValue;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
