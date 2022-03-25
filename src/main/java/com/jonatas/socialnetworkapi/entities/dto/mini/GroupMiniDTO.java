package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.Group;

public class GroupMiniDTO {

	private String id;
	private String name;
	private String description;
	private UserMiniDTO creator;
	private String creationDate;
	
	private int QuantityMembers;
	private int QuantityModertors;
	private int QuantitySilenced;
	private int QuantityPosts;
	
	public GroupMiniDTO(Group group) {
		super();
		this.id = group.getId();
		this.name = group.getName();
		this.description = group.getDescription();
		this.creator = group.getCreator() != null ? new UserMiniDTO(group.getCreator()) : null;
		this.creationDate = group.getCreationDate();
		this.QuantityMembers = group.getMembers().size();
		this.QuantityModertors = group.getModerators().size();
		this.QuantitySilenced = group.getMembersSilenced().size();
		this.QuantityPosts = group.getPosts().size();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserMiniDTO getCreator() {
		return creator;
	}

	public void setCreator(UserMiniDTO creator) {
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
		QuantityMembers = quantityMembers;
	}

	public int getQuantityModertors() {
		return QuantityModertors;
	}

	public void setQuantityModertors(int quantityModertors) {
		QuantityModertors = quantityModertors;
	}

	public int getQuantitySilenced() {
		return QuantitySilenced;
	}

	public void setQuantitySilenced(int quantitySilenced) {
		QuantitySilenced = quantitySilenced;
	}

	public int getQuantityPosts() {
		return QuantityPosts;
	}

	public void setQuantityPosts(int quantityPosts) {
		QuantityPosts = quantityPosts;
	}
	
	
	
	
}
