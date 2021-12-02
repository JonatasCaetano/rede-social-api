package com.jonatas.socialnetworkapi.dto;

import com.jonatas.socialnetworkapi.entities.User;

public class AuthorDTO {

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
	
	
}
