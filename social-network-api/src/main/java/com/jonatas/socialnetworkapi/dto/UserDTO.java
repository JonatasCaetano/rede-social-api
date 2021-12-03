package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;
import java.util.Objects;

import com.jonatas.socialnetworkapi.entities.User;

public class UserDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	private String invitation;
	private String password;

	
	public UserDTO() {
		super();
	}

	public UserDTO(String id, String name, String email, String invitation, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.invitation = invitation;
		this.password = password;
	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

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
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}


	
	
}
