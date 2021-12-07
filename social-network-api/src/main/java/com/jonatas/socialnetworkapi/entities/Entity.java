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
	private int type;
	
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
	private List<Evaluation> evaluations = new ArrayList<>();
	
	//builders
	
	public Entity() {
		super();
	}
	
	public Entity(String name, String image, String description, Date release, int type) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
		this.release = release;
		this.type = type;
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
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	
	public List<Evaluation> getEvaluations() {
		return evaluations;
	}
			
	public double getEvaluationAverage() {
		return evaluationAverage;
	}

	public void setEvaluationAverage() {
		this.evaluationAverage = this.evaluationSum / this.evaluationQuantity;
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
	
	//hashCode and equals

	@Override
	public int hashCode() {
		return Objects.hash(name);
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
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description
				+ ", release=" + release + ", type=" + type + ", season=" + season + ", evaluationAverage="
				+ evaluationAverage + ", evaluationSum=" + evaluationSum + ", evaluationQuantity=" + evaluationQuantity
				+ ", workers=" + workers + ", seasons=" + seasons + ", evaluations=" + evaluations + "]";
	}
	
	

	
}
