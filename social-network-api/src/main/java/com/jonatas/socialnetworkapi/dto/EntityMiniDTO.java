package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.Entity;

public class EntityMiniDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	private String id;
	private String name;
	private String image;
	private String description;
	
	//builders
	
	public EntityMiniDTO() {
		super();
	}

	public EntityMiniDTO(String id, String name, String image, String description) {
		super();
		this.id = id;
		this.name = name;
		this.setImage(image);
		this.description = description;
	}

	public EntityMiniDTO(Entity entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.setImage(entity.getImage());
		this.description = entity.getDescription();
	}
	
	//getters and setters

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
