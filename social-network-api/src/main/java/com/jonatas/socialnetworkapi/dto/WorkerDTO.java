package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

public class WorkerDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	private String user;
	private String entity;
	private String role;
	
	//builders
	
	public WorkerDTO() {
		super();
	}

	public WorkerDTO(String user, String entity, String role) {
		super();
		this.user = user;
		this.entity = entity;
		this.role = role;
	}
	
	//getters and setters

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
	
	
	
	
}
