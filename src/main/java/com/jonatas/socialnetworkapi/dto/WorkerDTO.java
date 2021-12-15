package com.jonatas.socialnetworkapi.dto;

public class WorkerDTO {
	
	private String idUser;
	private String idEntity;
	private String role;
		
	public WorkerDTO() {
		super();
	}

	public WorkerDTO(String user, String entity, String role) {
		super();
		this.idUser = user;
		this.idEntity = entity;
		this.role = role;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
