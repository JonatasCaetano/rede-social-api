package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class EditionDTO {

	private String idUser;
	private String idEntity;
	private String idSeason;
	private String idEpisode;
	private Date release;
	private Object previus;
	private Object current;
	private String attribute;
	
	public EditionDTO() {
		super();
	}

	public EditionDTO(String user, String entity, String season, String episode, Date release, Object previus,
			Object current, String attribute) {
		super();
		this.idUser = user;
		this.idEntity = entity;
		this.idSeason = season;
		this.idEpisode = episode;
		this.release = release;
		this.previus = previus;
		this.current = current;
		this.attribute = attribute;
	}

	public String getUser() {
		return idUser;
	}

	public void setUser(String user) {
		this.idUser = user;
	}

	public String getEntity() {
		return idEntity;
	}

	public void setEntity(String entity) {
		this.idEntity = entity;
	}

	public String getSeason() {
		return idSeason;
	}

	public void setSeason(String season) {
		this.idSeason = season;
	}

	public String getEpisode() {
		return idEpisode;
	}

	public void setEpisode(String episode) {
		this.idEpisode = episode;
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
