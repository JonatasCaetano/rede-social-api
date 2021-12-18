package com.jonatas.socialnetworkapi.dto;

public class WorkerDTO {
	
	private String idUser;
	private String idEntity;
	private String role;
	private int typeEntity = 1;
		
	public WorkerDTO() {
		super();
	}

	public WorkerDTO(String user, String entity, String role, int typeEntity) {
		super();
		this.idUser = user;
		this.idEntity = entity;
		this.role = role;
		this.typeEntity = typeEntity;
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

	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
	}
}
