package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class EditionDTO {

	private String user;
	private String entity;
	private String season;
	private String episode;
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
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.release = release;
		this.previus = previus;
		this.current = current;
		this.attribute = attribute;
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

	@Override
	public String toString() {
		return "EditionDTO [user=" + user + ", entity=" + entity + ", season=" + season + ", episode=" + episode
				+ ", release=" + release + ", previus=" + previus + ", current=" + current + ", attribute=" + attribute
				+ "]";
	}

	
	
	
}
