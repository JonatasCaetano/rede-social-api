package com.jonatas.socialnetworkapi.dto.mini;

import com.jonatas.socialnetworkapi.entities.Episode;

public class EpisodeMiniDTO {

	//attributes
	
	private String id;
	private String name;
	private String image;
	private String description;
	private int number;
	private int evaluationQuantity = 0;
	private double evaluationAverage = 0.0;
	private int typeEntity = 3;
	
	//builders
	
	public EpisodeMiniDTO() {
		super();
	}

	public EpisodeMiniDTO(Episode episode) {
		super();
		this.id = episode.getId();
		this.name = episode.getName();
		this.image = episode.getImage();
		this.description = episode.getDescription();
		this.number = episode.getNumber();
		this.evaluationAverage = episode.getEvaluationAverage();
		this.evaluationQuantity = episode.getEvaluationQuantity();
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
	}


	
	
}
