package com.jonatas.socialnetworkapi.entities.dto;

import com.jonatas.socialnetworkapi.enuns.Level;

public class WorkerDTO {
	
	private String idUser;
	private String idEntity;
	private String role;
	private Level level;
		
	public WorkerDTO() {
		super();
	}
	
	public WorkerDTO(String idUser, String idEntity, String role, Level level) {
		super();
		this.idUser = idUser;
		this.idEntity = idEntity;
		this.role = role;
		this.level = level;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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
