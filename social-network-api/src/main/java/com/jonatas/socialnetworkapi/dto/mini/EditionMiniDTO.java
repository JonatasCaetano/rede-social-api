package com.jonatas.socialnetworkapi.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Edition;

public class EditionMiniDTO {

	private String id;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	private Date release;
	private Object previus;
	private Object current;
	private String attribute;
	
	public EditionMiniDTO() {
		super();
	}
	
	public EditionMiniDTO(Edition edition) {
		super();
		this.id = edition.getId();
		this.user = new UserMiniDTO(edition.getUser());
		this.entity = new EntityMiniDTO(edition.getEntity());
		this.season = new SeasonMiniDTO(edition.getSeason());
		this.episode = new EpisodeMiniDTO(edition.getEpisode());
		this.release = edition.getRelease();
		this.previus = edition.getPrevius();
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

	public Object getPrevius() {
		return previus;
	}

	public void setPrevius(Object previus) {
		this.previus = previus;
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
