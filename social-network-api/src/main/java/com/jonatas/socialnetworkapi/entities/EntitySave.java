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
	private List<Season> seasons = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private List<Episode> episodes = new ArrayList<>();
	
	private int category;
	private boolean goal = false;
	private boolean rated = false;
	private boolean review = false;
	
	List<Date> historic = new ArrayList<>();

	public EntitySave() {
		super();
	}

	public EntitySave(User user, Entity entity, int category, boolean goal, boolean rated, boolean review) {
		super();
		this.user = user;
		this.entity = entity;
		this.category = category;
		this.goal = goal;
		this.rated = rated;
		this.review = review;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public List<Date> getHistoric() {
		return historic;
	}

	
	
	
}
