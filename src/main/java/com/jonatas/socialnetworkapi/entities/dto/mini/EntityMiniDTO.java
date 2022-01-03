package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.enuns.TypeEntity;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class EntityMiniDTO {
	
	private String id;
	private String name;
	private String description;
	private int evaluationQuantity = 0;
	private double evaluationAverage = 0.0;
	private int seasonQuantity = 0;
	private TypeObject typeObject = TypeObject.ENTITY;
	private TypeEntity typeEntity;
	private List<String> images = new ArrayList<>();
	private List<Map<String, String>> information = new ArrayList<>();
	
	public EntityMiniDTO() {
		super();
	}
	
	public EntityMiniDTO(Entity entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.evaluationQuantity = entity.getEvaluationQuantity();
		this.evaluationAverage = entity.getEvaluationAverage();
		this.seasonQuantity = entity.getSeasonQuantity();
		this.typeObject = entity.getTypeObject();
		this.typeEntity = entity.getTypeEntity();
		this.images = entity.getImages();
		this.information = entity.getInformation();
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

	public int getSeasonQuantity() {
		return seasonQuantity;
	}

	public void setSeasonQuantity(int seasonQuantity) {
		this.seasonQuantity = seasonQuantity;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public TypeEntity getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(TypeEntity typeEntity) {
		this.typeEntity = typeEntity;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<Map<String, String>> getInformation() {
		return information;
	}

	public void setInformation(List<Map<String, String>> information) {
		this.information = information;
	}
	
}
