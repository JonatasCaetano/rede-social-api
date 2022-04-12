package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.Group;
import com.jonatas.socialnetworkapi.entities.User;

public class GroupMicroDTO {

	private String id;
	private String name;
	private String image;
	private String description;
	
	private boolean userIsMember;
	private boolean userIsModerator;
	private boolean userIsSilenced;
	
	public GroupMicroDTO(Group group, User user) {
		super();
		this.id = group.getId();
		this.name = group.getName();
		this.description = group.getDescription();
		this.image = group.getImage();
		setUserIsMember(group, user);
		setUserIsModerator(group, user);
		setUserIsSilenced(group, user);

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

	public boolean isUserIsMember() {
		return userIsMember;
	}

	public void setUserIsMember(Group group, User user) {
		if(group.getMembers().contains(user) || group.getCreator().equals(user)) {
			userIsMember = true;
		}else {
			userIsMember = false;
		}
	}

	public boolean isUserIsModerator() {
		return userIsModerator;
	}

	public void setUserIsModerator(Group group, User user) {
		if(group.getModerators().contains(user) || group.getCreator().equals(user)) {
			userIsModerator = true;
		}else {
			userIsModerator = false;
		}
	}

	public boolean isUserIsSilenced() {
		return userIsSilenced;
	}

	public void setUserIsSilenced(Group group, User user) {
		if(group.getMembersSilenced().contains(user)) {
			userIsSilenced = true;
		}else {
			userIsSilenced = false;
		}
	}
	
	
}
