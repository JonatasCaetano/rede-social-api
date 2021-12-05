package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.Worker;

public class WorkerUserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	private EntityMiniDTO entity;
	private String role;
		
	//builders
	
	public WorkerUserDTO() {
		super();
	}

	public WorkerUserDTO(EntityMiniDTO entityDTO, String role) {
		super();
		this.entity = entityDTO;
		this.role = role;
	}
	
	public WorkerUserDTO(Worker worker) {
		super();
		this.entity = new EntityMiniDTO(worker.getEntity());
		this.role = worker.getRole();
	}
	
	//getters and setters

	public EntityMiniDTO getEntity() {
		return entity;
	}

	public void setEntity(EntityMiniDTO entity) {
		this.entity = entity;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	
}
