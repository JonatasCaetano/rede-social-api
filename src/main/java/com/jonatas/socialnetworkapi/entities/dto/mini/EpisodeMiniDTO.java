package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class EpisodeMiniDTO {
	
	private String id;
	private String name;
	private String description;
	private int numberEpisode;
	private List<String> images = new ArrayList<>();
	private int evaluationQuantity = 0;
	private double evaluationAverage = 0.0;
	private TypeObject typeObject = TypeObject.EPISODE;
	
	public EpisodeMiniDTO() {
		super();
	}

	public EpisodeMiniDTO(Episode episode) {
		super();
		this.id = episode.getId();
		this.name = episode.getName();
		this.images = episode.getImages();
		this.description = episode.getDescription();
		this.numberEpisode = episode.getNumberEpisode();
		this.evaluationAverage = episode.getEvaluationAverage();
		this.evaluationQuantity = episode.getEvaluationQuantity();
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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
	
	
	
}
