package com.jonatas.socialnetworkapi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypeTalk;

public class Talk {

	private String id;
	private Date release;
	private String title;
	private String body;
	private TypeTalk typeTalk;
	private TypeObject typeObject = TypeObject.TALK;
	List<String> images = new ArrayList<>();
	private int LikeQuantity = 0;
	@DBRef(lazy = true)
	@JsonBackReference
	private Entity entity;
	@DBRef(lazy = true)
	@JsonBackReference
	private Season season;
	@DBRef(lazy = true)
	@JsonBackReference
	private Episode episode;
	@DBRef(lazy = true)
	@JsonBackReference
	List<Comment> comments = new ArrayList<>();
	@DBRef(lazy = true)
	@JsonBackReference
	List<User> likes = new ArrayList<>();
	
	public Talk() {
		super();
	}

	public Talk(Date release, String title, String body, List<String> images, Entity entity, Season season,
			Episode episode, TypeTalk typeTalk) {
		super();
		this.release = release;
		this.title = title;
		this.body = body;
		this.images = images;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.typeTalk = typeTalk;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public TypeTalk getTypeTalk() {
		return typeTalk;
	}

	public void setTypeTalk(TypeTalk typeTalk) {
		this.typeTalk = typeTalk;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public int getLikeQuantity() {
		return LikeQuantity;
	}

	public void setLikeQuantity(int likeQuantity) {
		LikeQuantity += likeQuantity;
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

	public String getId() {
		return id;
	}

	public List<String> getImages() {
		return images;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<User> getLikes() {
		return likes;
	}
	
	
	
}
