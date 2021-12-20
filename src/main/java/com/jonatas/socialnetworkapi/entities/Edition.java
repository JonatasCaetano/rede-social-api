package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Edition implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private User user;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Entity entity;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Season season;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Episode episode;
	private int typeEntity;
	private Date release;
	private Object previous;
	private Object current;
	private String attribute;
	private TypeObject typeObject = TypeObject.EDITION;
	
	public Edition() {
		super();
	}

	public Edition(int typeEntity, User user, Entity entity, Season season, Episode episode, Date release, Object previous,
			Object current, String attribute) {
		super();
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.release = release;
		this.previous = previous;
		this.current = current;
		this.attribute = attribute;
		this.typeEntity = typeEntity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
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

	public String getId() {
		return id;
	}

	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
	}
	
	public TypeObject getTypeObject() {
		return typeObject;
	}
}
