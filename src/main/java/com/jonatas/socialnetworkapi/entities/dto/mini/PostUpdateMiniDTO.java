package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.post.Update;
import com.jonatas.socialnetworkapi.enuns.Level;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePost;

public class PostUpdateMiniDTO {

	private String id;
	private String release;
	private String body;
	private int category;
	private UserMiniDTO user;
	private EntityMiniDTO entity;
	private SeasonMiniDTO season;
	private EpisodeMiniDTO episode;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	private TypeObject typeObject = TypeObject.POST;
	private Level level;
	private TypePost typePost;
	private boolean spoiler;
	
	public PostUpdateMiniDTO() {
		super();
	}
	
	public PostUpdateMiniDTO(Update post) {
		super();
		this.id = post.getId();
		this.release = post.getRelease();
		this.body = post.getBody();
		this.category = post.getCategory();
		this.user = post.getUser() != null ? new UserMiniDTO(post.getUser()) : null;
		this.entity = post.getEntity() != null ? new EntityMiniDTO(post.getEntity()) : null;
		this.season = post.getSeason() != null ? new SeasonMiniDTO(post.getSeason()) : null;
		this.episode = post.getEpisode() != null ? new EpisodeMiniDTO(post.getEpisode()) : null;
		this.likeQuantity = post.getLikeQuantity();
		this.commentQuantity = post.getCommentQuantity();
		this.level = post.getLevel();
		this.spoiler = post.getSpoiler();
		this.typePost = post.getTypePost();
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isSpoiler() {
		return spoiler;
	}

	public void setSpoiler(boolean spoiler) {
		this.spoiler = spoiler;
	}

	public TypePost getTypePost() {
		return typePost;
	}

	public void setTypePost(TypePost typePost) {
		this.typePost = typePost;
	}

	
}
