package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;
import java.util.Objects;

import com.jonatas.socialnetworkapi.entities.Entity;

public class EntityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String description;
	
	public EntityDTO() {
		super();
	}

	public EntityDTO(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public EntityDTO(Entity entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityDTO other = (EntityDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
