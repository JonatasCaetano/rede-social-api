package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private Date release;
	private String body;
	private TypeObject typeObject = TypeObject.COMMENT;
	private int likeQuantity = 0;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	List<User> likes = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private User user;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Post post;

	public Comment() {
		super();
	}

	public Comment(Date release, String body, User user, Post post) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getId() {
		return id;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public int getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(int likeQuantity) {
		this.likeQuantity += likeQuantity;
	}
	
	
}
