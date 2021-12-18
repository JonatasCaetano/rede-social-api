package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jonatas.socialnetworkapi.entities.dto.mini.EntityMiniDTO;

@Document
public class Entity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	@Id
	private String id;
	
	private String name;
	private String image;
	private String description;
	private Date release;
	private String genre;
	
	private int typeObject = 1;
	private int typeEntity;
	private int season = 0;
	private double evaluationAverage = 0.0;
	private double evaluationSum = 0.0;
	private int evaluationQuantity = 0;
		
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Worker> workers = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Season> seasons = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Edition> editions = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<EntitySave> entitySaves = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Post> posts = new ArrayList<>();
	
	//builders
	
	public Entity() {
		super();
	}
	
	public Entity(String name, String image, String description, Date release, String genre, int typeEntity) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
		this.release = release;
		this.genre = genre;
		this.typeEntity = typeEntity;
	}
	
	public Entity(EntityMiniDTO entityMiniDTO) {
		super();
		this.name = entityMiniDTO.getName();
		this.image = entityMiniDTO.getImage();
		this.description = entityMiniDTO.getDescription();
		this.genre = entityMiniDTO.getGenre();
		this.typeEntity = entityMiniDTO.getTypeEntity();
	}

	
	//getters and setters

	public String getId() {
		return id;
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
	
	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}
	
	public List<Worker> getWorkers() {
		return workers;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season += season;
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

	public int getTypeObject() {
		return typeObject;
	}


	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
	}

	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	//hashCode and equals

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return Objects.equals(id, other.id);
	}

	
	

	
	
	

	
}
