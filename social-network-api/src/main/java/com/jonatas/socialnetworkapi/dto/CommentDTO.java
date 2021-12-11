package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class CommentDTO {

	private String id;
	private Date release;
	private String body;
	private String user;
	private String post;
		
	public CommentDTO() {
		super();
	}

	public CommentDTO(Date release, String body, String user, String post) {
		super();
		this.release = release;
		this.body = body;
		this.user = user;
		this.post = post;
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
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
