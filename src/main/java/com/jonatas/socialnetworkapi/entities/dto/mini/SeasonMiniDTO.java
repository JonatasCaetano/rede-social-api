package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class SeasonMiniDTO {


	private String id;
	private String name;
	private String image;
	private String description;
	private int numberSeason;
	
	private int evaluationQuantity = 0;
	private double evaluationAverage = 0.0;
	

	private TypeObject typeObject = TypeObject.SEASON;
	private EntityMiniDTO entity;
	
	private int episodeQuantity = 0;
	
	private int category1 = 0;
	private int category2 = 0;
	private int category3 = 0;
	private int category4 = 0;
	
		
	public SeasonMiniDTO() {
		super();
	}

	public SeasonMiniDTO(Season season) {
		super();
		this.id = season.getId();
		this.name = season.getName();
		this.image = season.getImage();
		this.description = season.getDescription();
		this.numberSeason = season.getNumberSeason();
		this.evaluationAverage = season.getEvaluationAverage();
		this.episodeQuantity = season.getEpisodeQuantity();
		this.evaluationQuantity = season.getEvaluationQuantity();
		this.entity = season.getEntity() != null ? new EntityMiniDTO(season.getEntity()) : null;
		this.category1 = season.getCategory1();
		this.category2 = season.getCategory2();
		this.category3 = season.getCategory3();
		this.category4 = season.getCategory4();
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

	public int getCategory1() {
		return category1;
	}

	public void setCategory1(int category1) {
		this.category1 = category1;
	}

	public int getCategory2() {
		return category2;
	}

	public void setCategory2(int category2) {
		this.category2 = category2;
	}

	public int getCategory3() {
		return category3;
	}

	public void setCategory3(int category3) {
		this.category3 = category3;
	}

	public int getCategory4() {
		return category4;
	}

	public void setCategory4(int category4) {
		this.category4 = category4;
	}
	
	
	
	
}
