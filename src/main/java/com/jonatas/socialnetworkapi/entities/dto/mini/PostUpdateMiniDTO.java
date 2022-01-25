package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class PostUpdateMiniDTO {

	private String id;
	private Date release;
	private String body;
	private int category;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	private TypeObject typeObject = TypeObject.POST;
	
	public PostUpdateMiniDTO() {
		super();
	}
	
	public PostUpdateMiniDTO(Post post) {
		super();
		this.id = post.getId();
		this.release = post.getRelease();
		this.body = post.getBody();
		this.user = post.getUser() != null ? new UserMiniDTO(post.getUser()) : null;
		this.commentQuantity = post.getCommentQuantity();
		this.likeQuantity = post.getLikeQuantity();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(int likeQuantity) {
		this.likeQuantity = likeQuantity;
	}

	public int getCommentQuantity() {
		return commentQuantity;
	}

	public void setCommentQuantity(int commentQuantity) {
		this.commentQuantity = commentQuantity;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}
}
