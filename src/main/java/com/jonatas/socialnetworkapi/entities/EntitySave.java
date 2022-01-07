package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeEntitySave;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class EntitySave implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private TypeEntitySave typeEntitySave;
	private int category;
	private boolean isGoal = false;
	private boolean isRated = false;
	private boolean isReview = false;
	private int evaluation;
	private String review;
	private TypeObject typeObject = TypeObject.ENTITY_SAVE;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private User user;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Entity entity;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Season season;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Episode episode;
			
	List<String> historic = new ArrayList<>();

	public EntitySave() {
		super();
	}

	public EntitySave(User user, Entity entity, Season season, Episode episode, int category, boolean isGoal,
			boolean isRated, boolean isReview, TypeEntitySave typeEntitySave) {
		super();
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.category = category;
		this.isGoal = isGoal;
		this.isRated = isRated;
		this.isReview = isReview;
		this.typeEntitySave = typeEntitySave;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public boolean isGoal() {
		return isGoal;
	}

	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}

	public boolean isRated() {
		return isRated;
	}

	public void setRated(boolean isRated) {
		this.isRated = isRated;
	}

	public boolean isReview() {
		return isReview;
	}

	public void setReview(boolean isReview) {
		this.isReview = isReview;
	}

	public String getId() {
		return id;
	}

	public List<String> getHistoric() {
		return historic;
	}
	
	public TypeObject getTypeObject() {
		return typeObject;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public TypeEntitySave getTypeEntitySave() {
		return typeEntitySave;
	}

	public void setTypeEntitySave(TypeEntitySave typeEntitySave) {
		this.typeEntitySave = typeEntitySave;
	}

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
		EntitySave other = (EntitySave) obj;
		return Objects.equals(id, other.id);
	}	
	
}
