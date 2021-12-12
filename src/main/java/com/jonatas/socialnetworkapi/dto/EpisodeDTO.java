package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Episode;

public class EpisodeDTO {

	//attributes
	
	private String id;
	private String name;
	private String description;
	private String image;
	private Date release;
	private int number;
	
	//builders
	
	public EpisodeDTO() {
		super();
	}

	public EpisodeDTO(String name, String description, String image, Date release, int number) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.release = release;
		this.number = number;
	}
	
	public EpisodeDTO(Episode episode) {
		super();
		this.id = episode.getId();
		this.name = episode.getName();
		this.description = episode.getDescription();
		this.image = episode.getImage();
		this.release = episode.getRelease();
		this.number = episode.getNumber();
	}
	
	//getters and setters

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

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
