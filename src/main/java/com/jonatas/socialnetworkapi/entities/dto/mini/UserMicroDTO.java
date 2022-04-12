package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.User;

public class UserMicroDTO {

	private String id;
	private String name;
	private String imageProfile;
	private boolean following;
	private boolean follower;
	
	public UserMicroDTO() {
		super();
	}

	public UserMicroDTO(User user, User send) {
		super();
		this.id = user.getId();
		this.name = user.getEmail();
		this.imageProfile = user.getImageProfile();
		setFollowing(user, send);
		setFollower(user, send);
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
		
	public boolean isFollowing() {
		return following;
	}

	public void setFollowing(User user, User send) {
		if(user.getFollower().getFollowing().contains(send)) {
			following = true;
		}else {
			following = false;
		}
	}
	
	public boolean isFollower() {
		return follower;
	}

	public void setFollower(User user, User send) {
		if(user.getFollower().getFollowing().contains(send)) {
			follower = true;
		}else {
			follower = false;
		}
	}
	
	
}
