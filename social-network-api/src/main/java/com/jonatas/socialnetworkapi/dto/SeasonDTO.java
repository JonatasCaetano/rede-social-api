package com.jonatas.socialnetworkapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Season;

public class SeasonDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String image;
	private String description;
	private Date release;
	private int number;
	private int episode = 0;
	
	public SeasonDTO() {
		super();
	}
	
	public SeasonDTO(String name, String description, String image, Date release, int number) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.release = release;
		this.number = number;
	}
	
	public SeasonDTO(Season season) {
		super();
		this.id = season.getId();
		this.name = season.getName();
		this.description = season.getDescription();
		this.image = season.getImage();
		this.release = season.getRelease();
		this.number = season.getNumber();
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

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
