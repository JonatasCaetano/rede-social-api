package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;

import com.jonatas.socialnetworkapi.entities.User;

public class UserMiniDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes

	private String id;
	private String name;
	private String image;
		
	//builders
	
	public UserMiniDTO() {
		super();
	}

	public UserMiniDTO(String id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public UserMiniDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.image = user.getImage();
	}
	
	//getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


}
