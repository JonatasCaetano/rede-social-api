package com.jonatas.socialnetworkapi.entities.dto;

import com.jonatas.socialnetworkapi.enuns.TypeWorker;

public class WorkerDTO {
	
	private String idUser;
	private String idEntity;
	private String role;
	private TypeWorker typeWorker;
		
	public WorkerDTO() {
		super();
	}
	
	public WorkerDTO(String idUser, String idEntity, String role, TypeWorker typeWorker) {
		super();
		this.idUser = idUser;
		this.idEntity = idEntity;
		this.role = role;
		this.typeWorker = typeWorker;
	}

	public TypeWorker getTypeWorker() {
		return typeWorker;
	}


	public void setTypeWorker(TypeWorker typeWorker) {
		this.typeWorker = typeWorker;
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
