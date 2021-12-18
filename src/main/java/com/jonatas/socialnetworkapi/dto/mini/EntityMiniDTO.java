package com.jonatas.socialnetworkapi.dto.mini;

import com.jonatas.socialnetworkapi.entities.Entity;

public class EntityMiniDTO {
	
	//attributes
	
	private String id;
	private String name;
	private String image;
	private String description;
	private int evaluationQuantity = 0;
	private double evaluationAverage = 0.0;
	private int season = 0;
	private int typeEntity = 1;
	private String genre;
	
	//builders
	
	public EntityMiniDTO() {
		super();
	}

	public EntityMiniDTO(Entity entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.image = entity.getImage();
		this.description = entity.getDescription();
		this.evaluationAverage = entity.getEvaluationAverage();
		this.season = entity.getSeason();
		this.evaluationQuantity = entity.getEvaluationQuantity();
		this.setGenre(entity.getGenre());
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
	
	public int getEvaluationQuantity() {
		return evaluationQuantity;
	}

	public void setEvaluationQuantity(int evaluationQuantity) {
		this.evaluationQuantity = evaluationQuantity;
	}

	public double getEvaluationAverage() {
		return evaluationAverage;
	}

	public void setEvaluationAverage(double evaluationAverage) {
		this.evaluationAverage = evaluationAverage;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
	}
	
	
	
}
