package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;
import java.util.Objects;

import com.jonatas.socialnetworkapi.entities.User;

public class AuthorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public AuthorDTO() {
		super();
	}

	public AuthorDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public AuthorDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
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
		AuthorDTO other = (AuthorDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
