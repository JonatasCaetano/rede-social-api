package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

import com.jonatas.socialnetworkapi.dto.mini.EntityMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.EpisodeMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.SeasonMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.entities.Evaluation;

public class EvaluationUserDTO {

	//attributes
	
	private String id;
	private int type;
	private double value;
	private Date release;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	
	
	//builders
	
	public EvaluationUserDTO() {
		super();
	}

	public EvaluationUserDTO(String id, UserMiniDTO user, EntityMiniDTO entity, SeasonMiniDTO season,
			EpisodeMiniDTO episode, double value, Date release, int type) {
		super();
		this.id = id;
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.value = value;
		this.release = release;
		this.setType(type);
	}
	
	public EvaluationUserDTO(Evaluation evaluation) {
		this.id = evaluation.getId();
		this.user = evaluation.getUser() != null ? new UserMiniDTO(evaluation.getUser()) : null;
		this.entity = evaluation.getEntity() != null ? new EntityMiniDTO(evaluation.getEntity()) : null;
		this.season = evaluation.getSeason() != null ? new SeasonMiniDTO(evaluation.getSeason()): null;
		this.episode = evaluation.getEpisode() != null ? new EpisodeMiniDTO(evaluation.getEpisode()) : null;
		this.value = evaluation.getValue();
		this.release = evaluation.getRelease();
		this.setType(evaluation.getType());
	}

	//getters and setters
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
