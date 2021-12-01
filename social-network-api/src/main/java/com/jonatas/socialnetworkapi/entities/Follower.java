package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document
public class Follower implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String user;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private List<User> following = new ArrayList<>();

	public Follower() {
		
	}

	public Follower(String id, String user) {
		this.id = id;
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public List<User> getFollowing() {
		return following;
	} 
	
	
	
	
}
