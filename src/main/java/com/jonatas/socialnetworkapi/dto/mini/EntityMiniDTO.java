package com.jonatas.socialnetworkapi.dto.mini;

import java.util.Date;

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
	private int type;
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
		this.type = entity.getType();
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}
