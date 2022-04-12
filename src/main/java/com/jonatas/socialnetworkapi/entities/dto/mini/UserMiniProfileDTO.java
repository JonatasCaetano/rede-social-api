package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class UserMiniProfileDTO {
	
	private String id;
	private String name;
	private String imageProfile;
	private String description;
	private boolean status;
	private int quantityFollowing = 0;
	private int quantityFollowers = 0;
	private TypeObject typeObject = TypeObject.USER;
	private boolean following;
	private boolean follower;
		
	
	public UserMiniProfileDTO() {
		super();
	}
	
	public UserMiniProfileDTO(User user, User send) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.imageProfile = user.getImageProfile();
		this.description = user.getDescription();
		this.quantityFollowing = user.getQuantityFollowing();
		this.quantityFollowers = user.getQuantityFollowers();
		this.status = user.isStatus();
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getQuantityFollowing() {
		return quantityFollowing;
	}

	public void setQuantityFollowing(int quantityFollowing) {
		this.quantityFollowing = quantityFollowing;
	}

	public int getQuantityFollowers() {
		return quantityFollowers;
	}

	public void setQuantityFollowers(int quantityFollowers) {
		this.quantityFollowers = quantityFollowers;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
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
