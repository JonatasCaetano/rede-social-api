package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.enuns.Level;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class WorkerMiniDTO {

	private String id;
	private Date release;
	private String role;
	private Level level;
	private TypeObject typeObject = TypeObject.WORKER;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	
	public WorkerMiniDTO() {
		super();
	}
	
	public WorkerMiniDTO(Worker worker) {
		super();
		this.id = worker.getId();
		this.release = worker.getRelease();
		this.role = worker.getRole();
		this.user = worker.getUser() != null ? new UserMiniDTO(worker.getUser()) : null;
		this.entity = worker.getEntity() != null ? new EntityMiniDTO(worker.getEntity()) : null;
		this.level = worker.getLevel();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public EntityMiniDTO getEntity() {
		return entity;
	}

	public void setEntity(EntityMiniDTO entity) {
		this.entity = entity;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	
	
	
}
