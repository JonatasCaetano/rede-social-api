package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeComment;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//variables
	
	@Id
	private String id;
	
	private String release;
	private String body;
	private TypeObject typeObject = TypeObject.COMMENT;
	private int likeQuantity = 0;
	private TypeComment typeComment;
	
	//References
	
	@JsonBackReference
	@DocumentReference(lazy = true, collection = "user")
	List<User> likes = new ArrayList<>();
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "user")
	private User author;
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "post")
	private Post post;
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "entitySave")
	private EntitySave entitySave;
	
	//References
	
	//variables

	public Comment() {
		super();
	}

	public Comment(String release, String body, User author, Post post, EntitySave entitySave, TypeComment typeComment) {
		super();
		this.release = release;
		this.body = body;
		this.author = author;
		this.post = post;
		this.entitySave = entitySave;
		this.typeComment = typeComment;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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
	
	public TypeComment getTypeComment() {
		return typeComment;
	}

	public void setTypeComment(TypeComment typeComment) {
		this.typeComment = typeComment;
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
