package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.post.Update;
import com.jonatas.socialnetworkapi.enuns.Level;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class PostUpdateMiniDTO {

	private String id;
	private String release;
	private String body;
	private int category;
	private UserMicroWidgetDTO author;
	private EntityMicroDTO entity;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	private TypeObject typeObject = TypeObject.POST;
	private TypePostVisibility typePostVisibility = TypePostVisibility.USER;
	private Level level;
	private TypePost typePost;
	private boolean spoiler;
	private int evaluation;
	private boolean liked;
	private UserMicroWidgetDTO like;
	
	public PostUpdateMiniDTO() {
		super();
	}
	
	public PostUpdateMiniDTO(Update post, User user) {
		super();
		this.id = post.getId();
		this.release = post.getRelease();
		this.body = post.getBody();
		this.category = post.getCategory();
		this.author = post.getAuthor() != null ? new UserMicroWidgetDTO(post.getAuthor()) : null;
		this.entity = post.getEntity() != null ? new EntityMicroDTO(post.getEntity()) : null;
		this.likeQuantity = post.getLikeQuantity();
		this.commentQuantity = post.getCommentQuantity();
		this.level = post.getLevel();
		this.spoiler = post.getSpoiler();
		this.typePost = post.getTypePost();
		this.evaluation = post.getEvaluation();
		setLike(post, user);
		setLiked(post, user);
		
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

	public UserMicroWidgetDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserMicroWidgetDTO author) {
		this.author = author;
	}

	public void setLiked(Update post, User user) {
		if(post.getLikes().contains(user)) {
			this.liked = true;
		}else {
			this.liked = false;
		}
	}
	
	public boolean isLiked() {
		return liked;
	}

	public UserMicroWidgetDTO getLike() {
		return like;
	}

	public void setLike(Update post, User user) {
		if(!post.getLikes().isEmpty()) {
			UserMicroWidgetDTO userMicroWidgetDTO = new UserMicroWidgetDTO(post.getLikes().get(0));
			if(userMicroWidgetDTO.getId().hashCode() != user.getId().hashCode()) {
				this.like = userMicroWidgetDTO;
			}else {
				if(post.getLikes().size() > 1) {
					userMicroWidgetDTO = new UserMicroWidgetDTO(post.getLikes().get(1));
					this.like = userMicroWidgetDTO;
				}
			}
		}
	}

	public EntityMicroDTO getEntity() {
		return entity;
	}

	public void setEntity(EntityMicroDTO entity) {
		this.entity = entity;
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

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public TypePostVisibility getTypePostVisibility() {
		return typePostVisibility;
	}

	public void setTypePostVisibility(TypePostVisibility typePostVisibility) {
		this.typePostVisibility = typePostVisibility;
	}

	
	
	
}
