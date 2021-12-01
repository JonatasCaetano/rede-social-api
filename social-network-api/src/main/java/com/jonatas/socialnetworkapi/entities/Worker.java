package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document
public class Worker  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@DBRef(lazy = true)
	@JsonManagedReference
	private User user;
	@DBRef(lazy = true)
	@JsonManagedReference
	private Entity entity;
	private String role;
	
	public Worker() {
	}

	public Worker(String id, User user, Entity entity, String role) {
		super();
		this.id = id;
		this.user = user;
		this.entity = entity;
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	
	
}
