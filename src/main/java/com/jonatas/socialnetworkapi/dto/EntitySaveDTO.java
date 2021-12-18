package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class EntitySaveDTO {

	private String idEntitySave;
	private String idUser;
	private String idEntity;
	private String idSeason;
	private String idEpisode;
	private int category;
	private Date release;
	private boolean goal;
	private boolean rated;
	private boolean review;
	private int typeEntity;
	
	public EntitySaveDTO() {
		super();
	}
	
	public EntitySaveDTO(String id, String user, String entity, String season, String episode, int category, Date release, int typeEntity) {
	super();
	this.idEntitySave = id;
	this.idUser = user;
	this.idEntity = entity;
	this.idSeason = season;
	this.idEpisode = episode;
	this.category = category;
	this.release = release;
	this.typeEntity = typeEntity;
	}

	public String getIdEntitySave() {
		return idEntitySave;
	}

	public void setIdEntitySave(String idEntitySave) {
		this.idEntitySave = idEntitySave;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	public String getIdSeason() {
		return idSeason;
	}

	public void setIdSeason(String idSeason) {
		this.idSeason = idSeason;
	}

	public String getIdEpisode() {
		return idEpisode;
	}

	public void setIdEpisode(String idEpisode) {
		this.idEpisode = idEpisode;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
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

	public boolean isReview() {
		return review;
	}

	public void setReview(boolean review) {
		this.review = review;
	}

	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
	}

	
}
