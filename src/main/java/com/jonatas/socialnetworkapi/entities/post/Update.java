package com.jonatas.socialnetworkapi.entities.post;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;

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
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Entity entity;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Season season;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Episode episode;
	
	public Update() {
		super();
	}
	
	public Update(Date release, String body, TypePost typePost, TypePostVisibility typePostVisibility, User user,
			int category, Level level, Entity entity, Season season, Episode episode) {
		super(release, body, typePost, typePostVisibility, user);
		this.category = category;
		this.level = level;
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
	
	

}
