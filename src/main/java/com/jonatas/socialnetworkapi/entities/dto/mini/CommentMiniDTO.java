package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.Comment;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class CommentMiniDTO {

	private String id;
	private String release;
	private String body;
	private UserMiniDTO user;
	private TypeObject typeObject = TypeObject.COMMENT;
	private int likeQuantity = 0;
	
	public CommentMiniDTO() {
		super();
	}
	
	public CommentMiniDTO(Comment comment) {
		super();
		this.id = comment.getId();
		this.release = comment.getRelease();
		this.body = comment.getBody();
		this.user = comment.getUser() != null ? new UserMiniDTO(comment.getUser()) : null;
		this.likeQuantity = comment.getLikeQuantity();
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

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public int getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(int likeQuantity) {
		this.likeQuantity = likeQuantity;
	}

}
