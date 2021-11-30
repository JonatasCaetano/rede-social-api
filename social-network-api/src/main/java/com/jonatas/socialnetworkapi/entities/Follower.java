package com.jonatas.socialnetworkapi.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Follower {

	@Id
	private String id;
	private String user;
	
	@DBRef(lazy = true)
	private List<User> following = new ArrayList<>();

	public Follower() {
		
	}

	public Follower(String id, String user, List<User> following) {
		super();
		this.id = id;
		this.user = user;
		this.following = following;
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
