package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class EvaluationDTO {
	
	//attributes


	private String id;
	private String user;
	private String entity;
	private String season;
	private String episode;
	private double value;
	private Date release;
	
	//builders
	
	public EvaluationDTO() {
		super();
	}

	public EvaluationDTO(String user, String entity, String season, String episode, double value, Date release) {
		super();
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.value = value;
		this.release = release;
	}
	
	//getters and setters

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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}
	
	
}