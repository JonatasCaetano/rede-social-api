package com.jonatas.socialnetworkapi.dto.mini;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Comment;

public class CommentMiniDTO {

	private String id;
	private Date release;
	private String body;
	private UserMiniDTO user;
	private PostMiniDTO post;
	
	public CommentMiniDTO() {
		super();
	}
	
	public CommentMiniDTO(Comment comment) {
		super();
		this.id = comment.getId();
		this.release = comment.getRelease();
		this.body = comment.getBody();
		this.user = comment.getUser() != null ? new UserMiniDTO(comment.getUser()) : null;
		this.post = comment.getPost() != null ? new PostMiniDTO(comment.getPost()) : null;
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

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public PostMiniDTO getPost() {
		return post;
	}

	public void setPost(PostMiniDTO post) {
		this.post = post;
	}
	
	
	
}