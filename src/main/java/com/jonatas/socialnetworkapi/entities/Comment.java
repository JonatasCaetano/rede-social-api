package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String release;
	private String body;
	private TypeObject typeObject = TypeObject.COMMENT;
	private int likeQuantity = 0;
	
	@DBRef(lazy = true)
	@JsonBackReference
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

	public Comment(String release, String body, User user, Post post) {
		super();
		this.release = release;
		this.body = body;
		this.user = user;
		this.post = post;
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

	public List<User> getLikes() {
		return likes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
