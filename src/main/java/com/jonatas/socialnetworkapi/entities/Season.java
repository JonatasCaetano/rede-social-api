package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Season implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String name;
	private String description;
	private List<String> images = new ArrayList<>();
		
	private int numberSeason;
	
	private int episodeQuantity = 0;
	private double evaluationAverage = 0.0;
	private double evaluationSum = 0.0;
	private int evaluationQuantity = 0;
	private TypeObject typeObject = TypeObject.SEASON;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Entity entity;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Episode> episodes = new ArrayList<>(); 
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Edition> editions = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<EntitySave> entitySaves = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Post> posts = new ArrayList<>();

	public Season() {
		super();
	}

	public Season(String name, String description, int numberSeason, Entity entity) {
		super();
		this.name = name;
		this.description = description;
		this.numberSeason = numberSeason;
		this.entity = entity;
	}
	
	public String getId() {
		return id;
	}
	
	public int getNumberSeason() {
		return numberSeason;
	}

	public void setNumberSeason(int numberSeason) {
		this.numberSeason = numberSeason;
	}

	public int getEpisodeQuantity() {
		return episodeQuantity;
	}

	public void setEpisodeQuantity(int episodeQuantity) {
		this.episodeQuantity += episodeQuantity;
	}

	public List<String> getImages() {
		return images;
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

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}
			
	public double getEvaluationAverage() {
		return evaluationAverage;
	}

	public void setEvaluationAverage() {
		if(this.evaluationSum == 0 || this.evaluationQuantity == 0) {
			this.evaluationAverage = 0.0;
		}else {
			this.evaluationAverage = this.evaluationSum / this.evaluationQuantity;
		}
	}

	public double getEvaluationSum() {
		return evaluationSum;
	}

	public void setEvaluationSum(double evaluationSum) {
		this.evaluationSum += evaluationSum;
	}

	public int getEvaluationQuantity() {
		return evaluationQuantity;
	}

	public void setEvaluationQuantity(int evaluationQuantity) {
		this.evaluationQuantity += evaluationQuantity;
	}
	
	public List<EntitySave> getEntitySaves() {
		return entitySaves;
	}
	
	public List<Edition> getEditions() {
		return editions;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public TypeObject getTypeObject() {
		return typeObject;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numberSeason);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Season other = (Season) obj;
		return numberSeason == other.numberSeason;
	}
}
