package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.enuns.TypeEntitySave;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class EntitySaveMiniDTO {

	private String id;
	private TypeEntitySave typeEntitySave;
	private int category;
	private boolean isGoal;
	private boolean isRated;
	private boolean isReview;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	private int evaluation;
	private String review;
	private TypeObject typeObject = TypeObject.ENTITY_SAVE;
	List<String> historic = new ArrayList<>();
	
	public EntitySaveMiniDTO() {
		super();
	}
	
	public EntitySaveMiniDTO(EntitySave entitySave) {
		super();
		this.id = entitySave.getId();
		this.typeEntitySave = entitySave.getTypeEntitySave();
		this.category = entitySave.getCategory();
		this.isGoal = entitySave.isGoal();
		this.isRated = entitySave.isRated();
		this.isReview = entitySave.isReview();
		this.user = entitySave.getUser()!= null ? new UserMiniDTO(entitySave.getUser()) : null;
		this.entity = entitySave.getEntity() != null? new EntityMiniDTO(entitySave.getEntity()) : null;
		this.season = entitySave.getSeason() != null? new SeasonMiniDTO(entitySave.getSeason()) : null;
		this.episode = entitySave.getEpisode() != null ? new EpisodeMiniDTO(entitySave.getEpisode()) : null;
		this.evaluation = entitySave.getEvaluation();
		this.review = entitySave.getReview();
		this.historic = entitySave.getHistoric();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeEntitySave getTypeEntitySave() {
		return typeEntitySave;
	}

	public void setTypeEntitySave(TypeEntitySave typeEntitySave) {
		this.typeEntitySave = typeEntitySave;
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

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public List<String> getHistoric() {
		return historic;
	}

	public void setHistoric(List<String> historic) {
		this.historic = historic;
	}

	
}
