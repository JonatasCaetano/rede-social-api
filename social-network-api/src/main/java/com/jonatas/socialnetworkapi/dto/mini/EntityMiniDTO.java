package com.jonatas.socialnetworkapi.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Entity;

public class EntityMiniDTO {
	
	//attributes
	
	private String id;
	private String name;
	private String image;
	private String description;
	private Date release;
	private double evaluationAverage = 0.0;
	private int season = 0;
	private int type;
	
	//builders
	
	public EntityMiniDTO() {
		super();
	}

	public EntityMiniDTO(String id, String name, String image, String description, double evaluationAverage,
			int season, Date release, int type) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.evaluationAverage = evaluationAverage;
		this.season = season;
		this.release = release;
		this.type = type;
	}

	public EntityMiniDTO(Entity entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.image = entity.getImage();
		this.description = entity.getDescription();
		this.evaluationAverage = entity.getEvaluationAverage();
		this.season = entity.getSeason();
		this.release = entity.getRelease();
		this.type = entity.getType();
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

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
