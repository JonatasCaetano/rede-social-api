package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.Worker;

public class WorkerEntityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private AuthorDTO authorDTO;
	private String role;
	
	public WorkerEntityDTO() {
		super();
	}

	public WorkerEntityDTO(AuthorDTO authorDTO, String role) {
		super();
		this.authorDTO = authorDTO;
		this.role = role;
	}
	
	public WorkerEntityDTO(Worker worker) {
		super();
		this.authorDTO = new AuthorDTO(worker.getUser());
		this.role = worker.getRole();
	}

	public AuthorDTO getAuthorDTO() {
		return authorDTO;
	}

	public void setAuthorDTO(AuthorDTO authorDTO) {
		this.authorDTO = authorDTO;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
