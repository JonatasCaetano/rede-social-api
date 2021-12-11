package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class PostDTO {

	private String id;
	private int type;
	private Date release;
	private String body;
	private int category;
	

	private String user;
	private String entity;
	private String season;
	private String episode;
	
	public PostDTO() {
		super();
	}

	public PostDTO(int type, Date release, String body, int category, String user, String entity, String season,
			String episode) {
		super();
		this.type = type;
		this.release = release;
		this.body = body;
		this.category = category;
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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
	
	
	
}
