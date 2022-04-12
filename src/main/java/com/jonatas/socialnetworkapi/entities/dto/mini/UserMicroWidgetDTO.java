package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.User;

public class UserMicroWidgetDTO {
	
	private String id;
	private String name;
	private String imageProfile;
	
	public UserMicroWidgetDTO() {
		super();
	}
	
	public UserMicroWidgetDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.imageProfile = user.getImageProfile();
		
	}

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
	
	public String getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}
	
}
