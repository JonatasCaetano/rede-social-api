package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.entities.User;

public class InvitationDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	//attributes
	
	private String id;
	private String value;
	private UserMiniDTO user;
	private List<User> invited = new ArrayList<>();
	
	//builders
	
	public InvitationDTO() {
		super();
	}
	
	public InvitationDTO(String id, String value, UserMiniDTO user, List<User> invited) {
		super();
		this.id = id;
		this.value = value;
		this.user = user;
		this.invited = invited;
	}

	public InvitationDTO(Invitation invitation) {
		super();
		this.id = invitation.getId();
		this.value = invitation.getValue();
		this.user = new UserMiniDTO(invitation.getUser());
		this.invited = invitation.getInvited();
	}

	//getters and setters
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public List<User> getInvited() {
		return invited;
	}

	public void setInvited(List<User> invited) {
		this.invited = invited;
	}


	
}
