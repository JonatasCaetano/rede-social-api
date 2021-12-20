package com.jonatas.socialnetworkapi.entities.dto;

import com.jonatas.socialnetworkapi.enuns.TypeEntity;

public class EntityDTO {

	private String name;
	private String image;
	private String description;
	private String genre;
	private TypeEntity typeEntity;
	
	public EntityDTO() {
		super();
	}

	public EntityDTO(String name, String image, String description, String genre, TypeEntity typeEntity) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
		this.genre = genre;
		this.typeEntity = typeEntity;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public TypeEntity getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(TypeEntity typeEntity) {
		this.typeEntity = typeEntity;
	}
}
