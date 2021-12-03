package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.entities.User;

public class InvitationDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String value;
	private AuthorDTO authorDTO;
	private List<User> invited = new ArrayList<>();
	
	public InvitationDTO() {
		super();
	}

	public InvitationDTO(String id, String value, AuthorDTO authorDTO, List<User> invited) {
		super();
		this.id = id;
		this.value = value;
		this.authorDTO = authorDTO;
		this.invited = invited;
	}
	
	public InvitationDTO(Invitation invitation) {
		super();
		this.id = invitation.getId();
		this.value = invitation.getValue();
		this.authorDTO = new AuthorDTO(invitation.getUser());
		this.invited = invitation.getInvited();
	}

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

	public AuthorDTO getAuthorDTO() {
		return authorDTO;
	}

	public void setAuthorDTO(AuthorDTO authorDTO) {
		this.authorDTO = authorDTO;
	}

	public List<User> getInvited() {
		return invited;
	}

	public void setInvited(List<User> invited) {
		this.invited = invited;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvitationDTO other = (InvitationDTO) obj;
		return Objects.equals(value, other.value);
	}

	
	
}
