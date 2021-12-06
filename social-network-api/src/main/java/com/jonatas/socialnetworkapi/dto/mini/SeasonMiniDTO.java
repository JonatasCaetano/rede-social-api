package com.jonatas.socialnetworkapi.dto.mini;

import com.jonatas.socialnetworkapi.entities.Season;

public class SeasonMiniDTO {

	//attributes
	
	private String id;
	private String name;
	private String image;
	private String description;
	private int number;
	private double evaluationAverage = 0.0;
	
	//builders
		
	public SeasonMiniDTO() {
		super();
	}
	
	public SeasonMiniDTO(String id, String name, String image, String description, int number,
		double evaluationAverage) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.number = number;
		this.evaluationAverage = evaluationAverage;
	}

	public SeasonMiniDTO(Season season) {
		super();
		this.id = season.getId();
		this.name = season.getName();
		this.image = season.getImage();
		this.description = season.getDescription();
		this.number = season.getNumber();
		this.evaluationAverage = season.getEvaluationAverage();
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

	public double getEvaluationAverage() {
		return evaluationAverage;
	}

	public void setEvaluationAverage(double evaluationAverage) {
		this.evaluationAverage = evaluationAverage;
	}
	
	
	
	
	
	
}
