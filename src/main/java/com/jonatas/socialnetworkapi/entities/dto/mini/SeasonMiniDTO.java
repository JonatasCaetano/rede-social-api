package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class SeasonMiniDTO {


	private String id;
	private String name;
	private List<String> images = new ArrayList<>();
	private String description;
	private int numberSeason;
	private int evaluationQuantity = 0;
	private double evaluationAverage = 0.0;
	private int episodeQuantity = 0;
	private TypeObject typeObject = TypeObject.SEASON;
	private EntityMiniDTO entity;
	
		
	public SeasonMiniDTO() {
		super();
	}

	public SeasonMiniDTO(Season season) {
		super();
		this.id = season.getId();
		this.name = season.getName();
		this.images = season.getImages();
		this.description = season.getDescription();
		this.numberSeason = season.getNumberSeason();
		this.evaluationAverage = season.getEvaluationAverage();
		this.episodeQuantity = season.getEpisodeQuantity();
		this.evaluationQuantity = season.getEvaluationQuantity();
		this.entity = season.getEntity() != null ? new EntityMiniDTO(season.getEntity()) : null;
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberSeason() {
		return numberSeason;
	}

	public void setNumberSeason(int numberSeason) {
		this.numberSeason = numberSeason;
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

	public int getEpisodeQuantity() {
		return episodeQuantity;
	}

	public void setEpisodeQuantity(int episodeQuantity) {
		this.episodeQuantity = episodeQuantity;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public EntityMiniDTO getEntity() {
		return entity;
	}

	public void setEntity(EntityMiniDTO entity) {
		this.entity = entity;
	}
	
	
}
