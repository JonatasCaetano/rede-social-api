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
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

@Document
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//variables
	
	@Id
	private String id;
	
	private String release;
	private String body;
	private TypePost typePost;
	private TypePostVisibility typePostVisibility;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	private TypeObject typeObject = TypeObject.POST;
	private Boolean spoiler;
	
	//References
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "user")
	private User author;
		
	@JsonBackReference
	@DocumentReference(lazy = true, collection = "comment")
	private List<Comment> comments = new ArrayList<>();
	
	@JsonManagedReference
	@DocumentReference(lazy = true, collection = "user")
	private List<User> likes = new ArrayList<>();
	
	//References
	
	//variables

	public Post() {
		super();
	}

	public Post(String release, String body, TypePost typePost, TypePostVisibility typePostVisibility, User author, Boolean spoiler) {
		super();
		this.release = release;
		this.body = body;
		this.typePost = typePost;
		this.typePostVisibility = typePostVisibility;
		this.author = author;
		this.spoiler = spoiler;
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

	public TypePost getTypePost() {
		return typePost;
	}

	public void setTypePost(TypePost typePost) {
		this.typePost = typePost;
	}

	public TypePostVisibility getTypePostVisibility() {
		return typePostVisibility;
	}

	public void setTypePostVisibility(TypePostVisibility typePostVisibility) {
		this.typePostVisibility = typePostVisibility;
	}

	public int getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(int likeQuantity) {
		this.likeQuantity += likeQuantity;
	}

	public int getCommentQuantity() {
		return commentQuantity;
	}

	public void setCommentQuantity(int commentQuantity) {
		this.commentQuantity += commentQuantity;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<User> getLikes() {
		return likes;
	}

	public Boolean getSpoiler() {
		return spoiler;
	}

	public void setSpoiler(Boolean spoiler) {
		this.spoiler = spoiler;
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
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}

}
