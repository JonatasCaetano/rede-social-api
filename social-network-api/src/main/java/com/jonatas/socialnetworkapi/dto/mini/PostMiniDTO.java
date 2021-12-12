package com.jonatas.socialnetworkapi.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Post;

public class PostMiniDTO {

	private String id;
	private int type;
	private Date release;
	private String body;
	private int category;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	private int comments = 0;
	private int likes = 0;
	
	public PostMiniDTO() {
		super();
	}
	
	public PostMiniDTO(Post post) {
		super();
		this.id = post.getId();
		this.type = post.getType();
		this.release = post.getRelease();
		this.body = post.getBody();
		this.category = post.getCategory();
		this.user = post.getUser() != null ? new UserMiniDTO(post.getUser()) : null;
		this.entity = post.getEntity() != null ? new EntityMiniDTO(post.getEntity()) : null;
		this.season = post.getSeason() != null ? new SeasonMiniDTO(post.getSeason()) : null;
		this.episode = post.getEpisode() != null ? new EpisodeMiniDTO(post.getEpisode()) : null;
		this.comments = post.getComments().size();
		this.likes = post.getLikes().size();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
	
}
