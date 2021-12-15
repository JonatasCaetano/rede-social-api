package com.jonatas.socialnetworkapi.dto.mini;

import com.jonatas.socialnetworkapi.entities.User;

public class UserMiniDTO {
	
	//attributes

	private String id;
	private String name;
	private String image;
	private String description;
	private String city;
	private boolean checked;
	private boolean privacy;
	private boolean status;
	private int following = 0;
	private int followers = 0;
		
	//builders
	
	public UserMiniDTO() {
		super();
	}
	
	public UserMiniDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.image = user.getImage();
		this.description = user.getDescription();
		this.city = user.getCity();
		this.checked = user.isChecked();
		this.following = user.getFollowing();
		this.followers = user.getFollowers();
		this.privacy = user.isPrivacy();
		this.status = user.isStatus();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	


	

}
