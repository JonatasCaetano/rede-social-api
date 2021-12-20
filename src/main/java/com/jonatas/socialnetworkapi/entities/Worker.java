package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypeWorker;

@Document
public class Worker  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private Date release;
	private String role;
	private TypeObject typeObject = TypeObject.WORKER;
	private TypeWorker typeWorker;
	@DBRef(lazy = true)
	@JsonManagedReference
	private User user;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Entity entity;
		
	public Worker() {
		super();
	}
	
	public Worker(Date release, String role, TypeWorker typeWorker, User user, Entity entity) {
		super();
		this.release = release;
		this.role = role;
		this.setTypeWorker(typeWorker);
		this.user = user;
		this.entity = entity;
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
	
	public TypeObject getTypeObject() {
		return typeObject;
	}
	
	public TypeWorker getTypeWorker() {
		return typeWorker;
	}

	public void setTypeWorker(TypeWorker typeWorker) {
		this.typeWorker = typeWorker;
	}

	@Override
	public int hashCode() {
		return Objects.hash(entity, role, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		return Objects.equals(entity, other.entity) && Objects.equals(role, other.role)
				&& Objects.equals(user, other.user);
	}
}
