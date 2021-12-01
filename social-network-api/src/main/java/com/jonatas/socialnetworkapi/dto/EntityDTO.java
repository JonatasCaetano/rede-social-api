package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

public class EntityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	
	public EntityDTO() {

	}

	public EntityDTO(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
