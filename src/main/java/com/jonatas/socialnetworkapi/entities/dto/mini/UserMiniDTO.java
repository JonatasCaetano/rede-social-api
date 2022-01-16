package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class UserMiniDTO {
	
	private String id;
	
	private String name;
	private List<String> images = new ArrayList<>();
	private String description;
	private String place;
	
	private String email;
	
	private boolean checked;
	private boolean privacy;
	private boolean status;
	
	private int quantityFollowing = 0;
	private int quantityFollowers = 0;
	private TypeObject typeObject = TypeObject.USER;
		
	
	public UserMiniDTO() {
		super();
	}
	
	public UserMiniDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.images = user.getImages();
		this.description = user.getDescription();
		this.place = user.getPlace();
		this.email = user.getEmail();
		this.checked = user.isChecked();
		this.quantityFollowing = user.getQuantityFollowing();
		this.quantityFollowers = user.getQuantityFollowers();
		this.privacy = user.isPrivacy();
		this.status = user.isStatus();
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
