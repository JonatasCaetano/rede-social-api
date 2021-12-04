package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Season;

public class SeasonDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String image;
	private String description;
	private Date release;
	private int number;
	private String entity;
	private int episode = 0;
	
	public SeasonDTO() {
		super();
	}
	
	public SeasonDTO(String name, String image, String description, Date release, int number, String entity) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
		this.release = release;
		this.number = number;
		this.entity = entity;
	}
	
	public SeasonDTO(Season season) {
		super();
		this.name = season.getName();
		this.image = season.getImage();
		this.description = season.getDescription();
		this.release = season.getRelease();
		this.number = season.getNumber();
		this.entity = season.getEntity().getId() != null ? season.getEntity().getId() : null;
		this.episode = season.getEpisode();
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

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}
	
	
	
	
}
