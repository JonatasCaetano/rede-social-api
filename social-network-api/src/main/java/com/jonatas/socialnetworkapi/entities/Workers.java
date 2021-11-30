package com.jonatas.socialnetworkapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Workers {
	
	@Id
	private String id;
	private String user;
	private String entity;
	private String role;
	
	public Workers() {
	}

	public Workers(String id, String user, String entity, String role) {
		super();
		this.id = id;
		this.user = user;
		this.entity = entity;
		this.role = role;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	
	
	
}
