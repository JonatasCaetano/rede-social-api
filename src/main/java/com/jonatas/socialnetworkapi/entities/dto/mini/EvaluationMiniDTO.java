package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Evaluation;

public class EvaluationMiniDTO {

	private String id;
	private int typeEntity;
	private double value;
	private Date release;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	
	public EvaluationMiniDTO() {
		super();
	}
	
	public EvaluationMiniDTO(Evaluation evaluation) {
		super();
		this.id = evaluation.getId();
		this.typeEntity = evaluation.getTypeEntity();
		this.value = evaluation.getValue();
		this.release = evaluation.getRelease();
		this.user = evaluation.getUser() != null ? new UserMiniDTO(evaluation.getUser()) : null;
		this.entity = evaluation.getEntity() != null ? new EntityMiniDTO(evaluation.getEntity()) : null;
		this.season = evaluation.getSeason() != null ? new SeasonMiniDTO(evaluation.getSeason()) : null;
		this.episode = evaluation.getEpisode() != null ? new EpisodeMiniDTO(evaluation.getEpisode()) : null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
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

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public EntityMiniDTO getEntity() {
		return entity;
	}

	public void setEntity(EntityMiniDTO entity) {
		this.entity = entity;
	}

	public SeasonMiniDTO getSeason() {
		return season;
	}

	public void setSeason(SeasonMiniDTO season) {
		this.season = season;
	}

	public EpisodeMiniDTO getEpisode() {
		return episode;
	}

	public void setEpisode(EpisodeMiniDTO episode) {
		this.episode = episode;
	}
	
	
	
}
