package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Edition;

public class EditionMiniDTO {

	private String id;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	private String attribute;
	private Object previous;
	private Object current;
	private Date release;
	
	public EditionMiniDTO() {
		super();
	}
	
	public EditionMiniDTO(Edition edition) {
		super();
		this.id = edition.getId();
		this.user = edition.getUser() != null ? new UserMiniDTO(edition.getUser()) : null;
		this.entity = edition.getEntity() != null ? new EntityMiniDTO(edition.getEntity()): null;
		this.season = edition.getSeason() != null ? new SeasonMiniDTO(edition.getSeason()) : null;
		this.episode = edition.getEpisode() != null ? new EpisodeMiniDTO(edition.getEpisode()) : null;
		this.release = edition.getRelease();
		this.previous = edition.getPrevious();
		this.current = edition.getCurrent();
		this.attribute = edition.getAttribute();
	}

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

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public Object getPrevious() {
		return previous;
	}

	public void setPrevious(Object previous) {
		this.previous = previous;
	}

	public Object getCurrent() {
		return current;
	}

	public void setCurrent(Object current) {
		this.current = current;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}
