package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.Worker;

public class WorkerUserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityDTO entityDTO;
	private String role;
		
	public WorkerUserDTO() {
		super();
	}

	public WorkerUserDTO(EntityDTO entityDTO, String role) {
		super();
		this.entityDTO = entityDTO;
		this.role = role;
	}
	
	public WorkerUserDTO(Worker worker) {
		super();
		this.entityDTO = new EntityDTO(worker.getEntity());
		this.role = worker.getRole();
	}

	public EntityDTO getEntityDTO() {
		return entityDTO;
	}

	public void setEntityDTO(EntityDTO entityDTO) {
		this.entityDTO = entityDTO;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
