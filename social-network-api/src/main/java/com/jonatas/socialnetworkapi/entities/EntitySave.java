package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document
public class EntitySave implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
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
	
	private int category;
	private boolean review = false;
	private boolean goal = false;
	private boolean rated = false;
	
	List<Date> historic = new ArrayList<>();

	public EntitySave() {
		super();
	}

	public EntitySave(String id, User user, Entity entity, Season season, Episode episode, int category, boolean review,
			boolean goal, boolean rated) {
		super();
		this.id = id;
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.category = category;
		this.review = review;
		this.goal = goal;
		this.rated = rated;
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

	public boolean isReview() {
		return review;
	}

	public void setReview(boolean review) {
		this.review = review;
	}

	public boolean isGoal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}

	public String getId() {
		return id;
	}

	public List<Date> getHistoric() {
		return historic;
	}
	
	
}
