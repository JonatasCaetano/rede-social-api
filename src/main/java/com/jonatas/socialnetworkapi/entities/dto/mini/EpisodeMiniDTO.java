/*
package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class EpisodeMiniDTO {
	
	private String id;
	private String name;
	private String image;
	private String description;
	private int numberEpisode;
	
	private int evaluationQuantity = 0;
	private double evaluationAverage = 0.0;
	
	private TypeObject typeObject = TypeObject.EPISODE;
	private SeasonMiniDTO season;
	
	private int category1 = 0;
	private int category2 = 0;
	private int category3 = 0;
	private int category4 = 0;
	
	
	public EpisodeMiniDTO() {
		super();
	}

	public EpisodeMiniDTO(Episode episode) {
		super();
		this.id = episode.getId();
		this.name = episode.getName();
		this.image = episode.getImage();
		this.description = episode.getDescription();
		this.numberEpisode = episode.getNumberEpisode();
		this.evaluationAverage = episode.getEvaluationAverage();
		this.evaluationQuantity = episode.getEvaluationQuantity();
		this.season= episode.getSeason() != null ? new SeasonMiniDTO(episode.getSeason()) : null;
		this.category1 = episode.getCategory1();
		this.category2 = episode.getCategory2();
		this.category3 = episode.getCategory3();
		this.category4 = episode.getCategory4();
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

	public int getNumberEpisode() {
		return numberEpisode;
	}

	public void setNumberEpisode(int numberEpisode) {
		this.numberEpisode = numberEpisode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public SeasonMiniDTO getSeason() {
		return season;
	}

	public void setSeason(SeasonMiniDTO season) {
		this.season = season;
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

*/
