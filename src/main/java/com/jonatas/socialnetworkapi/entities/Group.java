package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Document
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//variables
	
	@Id
	private String id;
	
	private String name;
	private String description;
	private User creator;
	private String creationDate;
	
	private int QuantityMembers;
	private int QuantityModertors;
	private int QuantitySilenced;
	private int QuantityPosts;
	
	@JsonBackReference
	@DocumentReference(collection = "user")
	private List<User> members = new ArrayList<>();
	@JsonBackReference
	@DocumentReference(collection = "user")
	private List<User> moderators = new ArrayList<>();
	@JsonBackReference
	@DocumentReference(collection = "user")
	private List<User> membersSilenced = new ArrayList<>();
	@JsonBackReference
	@DocumentReference(collection = "post")
	private List<Post> posts = new ArrayList<>();
	
	//variables
	
	public Group() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getQuantityMembers() {
		return QuantityMembers;
	}

	public void setQuantityMembers(int quantityMembers) {
		QuantityMembers = this.members.size();
	}

	public int getQuantityModertors() {
		return QuantityModertors;
	}

	public void setQuantityModertors(int quantityModertors) {
		QuantityModertors = this.moderators.size();
	}

	public int getQuantitySilenced() {
		return QuantitySilenced;
	}

	public void setQuantitySilenced(int quantitySilenced) {
		QuantitySilenced = this.membersSilenced.size();
	}

	public int getQuantityPosts() {
		return QuantityPosts;
	}

	public void setQuantityPosts(int quantityPosts) {
		QuantityPosts = this.posts.size();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public List<User> getMembers() {
		return members;
	}

	public List<User> getModerators() {
		return moderators;
	}

	public List<User> getMembersSilenced() {
		return membersSilenced;
	}

	public List<Post> getPosts() {
		return posts;
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
		Group other = (Group) obj;
		return Objects.equals(id, other.id);
	}

}
