package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class CommentDTO {

	private String idComment;
	private Date release;
	private String body;
	private String idUser;
	private String idPost;
		
	public CommentDTO() {
		super();
	}

	public CommentDTO(Date release, String body, String user, String post) {
		super();
		this.release = release;
		this.body = body;
		this.idUser = user;
		this.idPost = post;
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

	public String getUser() {
		return idUser;
	}

	public void setUser(String user) {
		this.idUser = user;
	}

	public String getPost() {
		return idPost;
	}

	public void setPost(String post) {
		this.idPost = post;
	}

	public String getId() {
		return idComment;
	}

	public void setId(String id) {
		this.idComment = id;
	}
}
