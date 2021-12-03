package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.Worker;

public class WorkerEntityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UserMiniDTO user;
	private String role;
	
	public WorkerEntityDTO() {
		super();
	}
	
	public WorkerEntityDTO(UserMiniDTO user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

	public WorkerEntityDTO(Worker worker) {
		super();
		this.user = new UserMiniDTO(worker.getUser());
		this.role = worker.getRole();
	}

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
