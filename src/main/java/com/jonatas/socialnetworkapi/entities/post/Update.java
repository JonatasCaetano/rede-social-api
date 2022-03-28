package com.jonatas.socialnetworkapi.entities.post;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.enuns.Level;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class Update extends Post {
	private static final long serialVersionUID = 1L;
	
	private int category;
	private Level level;
	private int evaluation;
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "entity")
	private Entity entity;
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "season")
	private Season season;
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "episode")
	private Episode episode;
	
	public Update() {
		super();
	}
		
	public Update(String release, String body, TypePost typePost, TypePostVisibility typePostVisibility, User user,
			Boolean spoiler, int category, Level level, int evaluation, Entity entity, Season season, Episode episode) {
		super(release, body, typePost, typePostVisibility, user, spoiler);
		this.category = category;
		this.level = level;
		this.evaluation = evaluation;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
	

}
