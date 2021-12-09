package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class EntitySaveDTO {

	private String id;
	private String user;
	private String entity;
	private String season;
	private String episode;
	private int category;
	private Date release;
	private int type;
	
	public EntitySaveDTO() {
		super();
	}
	
	public EntitySaveDTO(String id, String user, String entity, String season, String episode, int category, Date release, int type) {
	super();
	this.id = id;
	this.user = user;
	this.entity = entity;
	this.season = season;
	this.episode = episode;
	this.category = category;
	this.release = release;
	this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	

	
	
	
}
