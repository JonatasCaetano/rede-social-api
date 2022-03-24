package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.Level;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Worker  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//variables
	
	@Id
	private String id;
	
	private Date release;
	private String role;
	private TypeObject typeObject = TypeObject.WORKER;
	private Level level;

	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "user")
	private User user;

	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "entity")
	private Entity entity;
	
	//variables
		
	public Worker() {
		super();
	}
	
	public Worker(Date release, String role, Level level, User user, Entity entity) {
		super();
		this.release = release;
		this.role = role;
		this.level = level;
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
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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
