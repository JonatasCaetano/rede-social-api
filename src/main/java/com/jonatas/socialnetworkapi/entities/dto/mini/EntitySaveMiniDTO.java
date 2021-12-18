package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.EntitySave;

public class EntitySaveMiniDTO {

	private String id;
	private int typeEntity;
	private int category;
	private boolean goal = false;
	private boolean rated = false;
	private boolean review = false;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	private EvaluationMiniDTO evaluation;
	List<Date> historic = new ArrayList<>();
	
	public EntitySaveMiniDTO() {
		super();
	}
	
	public EntitySaveMiniDTO(EntitySave entitySave) {
		super();
		this.id = entitySave.getId();
		this.typeEntity = entitySave.getTypeEntity();
		this.category = entitySave.getCategory();
		this.goal = entitySave.isGoal();
		this.rated = entitySave.isRated();
		this.review = entitySave.isReview();
		this.user = entitySave.getUser()!= null ? new UserMiniDTO(entitySave.getUser()) : null;
		this.entity = entitySave.getEntity() != null? new EntityMiniDTO(entitySave.getEntity()) : null;
		this.season = entitySave.getSeason() != null? new SeasonMiniDTO(entitySave.getSeason()) : null;
		this.episode = entitySave.getEpisode() != null ? new EpisodeMiniDTO(entitySave.getEpisode()) : null;
		this.evaluation = entitySave.getEvaluation() != null ? new EvaluationMiniDTO(entitySave.getEvaluation()) : null;
		this.historic = entitySave.getHistoric();
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public EvaluationMiniDTO getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(EvaluationMiniDTO evaluation) {
		this.evaluation = evaluation;
	}

	public List<Date> getHistoric() {
		return historic;
	}

	public void setHistoric(List<Date> historic) {
		this.historic = historic;
	}
	
	
	
}
