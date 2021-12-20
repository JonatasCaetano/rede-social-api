package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Evaluation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private int typeEntity;
	private double value;
	private Date release;
	private TypeObject typeObject = TypeObject.EVALUATION;
	
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
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private EntitySave entitySave;
		
	public Evaluation() {
		super();
	}

	public Evaluation(User user, Entity entity, Season season, Episode episode, double value, Date release, int typeEntity) {
		super();
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.value = value;
		this.release = release;
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
