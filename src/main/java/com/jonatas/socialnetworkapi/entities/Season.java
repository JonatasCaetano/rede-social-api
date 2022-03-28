/*

package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Season implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//variables
	
	@Id
	private String id;
	
	private String name;
	private String description;
	private String image;
	private int numberSeason;
	private int episodeQuantity = 0;
	private double evaluationAverage = 0.0;
	private double evaluationSum = 0.0;
	private int evaluationQuantity = 0;
	private TypeObject typeObject = TypeObject.SEASON;
	private int category1 = 0;
	private int category2 = 0;
	private int category3 = 0;
	private int category4 = 0;

	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "entity")
	private Entity entity;

	@JsonBackReference
	@DocumentReference(lazy = true, collection = "episode")
	private List<Episode> episodes = new ArrayList<>(); 

	@JsonBackReference
	@DocumentReference(lazy = true, collection = "edition")
	private List<Edition> editions = new ArrayList<>();

	@JsonBackReference
	@DocumentReference(lazy = true, collection = "entitySave")
	private List<EntitySave> entitySaves = new ArrayList<>();
	
	@JsonBackReference
	@DocumentReference(lazy = true, collection = "post")
	private List<Post> posts = new ArrayList<>();
	
	//variables

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
	
	public int getCategory1() {
		return category1;
	}

	public void setCategory1(int category1) {
		this.category1 += category1;
	}

	public int getCategory2() {
		return category2;
	}

	public void setCategory2(int category2) {
		this.category2 += category2;
	}

	public int getCategory3() {
		return category3;
	}

	public void setCategory3(int category3) {
		this.category3 += category3;
	}

	public int getCategory4() {
		return category4;
	}

	public void setCategory4(int category4) {
		this.category4 += category4;
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

*/
