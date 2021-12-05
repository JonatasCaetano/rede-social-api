package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Season;

public class SeasonEntityDTO {

	//attributes
	
	private String id;
	private String name;
	private String image;
	private Date release;
	private int number;
	private int episode = 0;
	
	//builders
	
	public SeasonEntityDTO() {
		super();
	}

	public SeasonEntityDTO(String id, String name, String image, Date release, int number) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.release = release;
		this.number = number;
	}
	
	public SeasonEntityDTO(Season season) {
		super();
		this.id = season.getId();
		this.name = season.getName();
		this.image = season.getImage();
		this.release = season.getRelease();
		this.number = season.getNumber();
		this.episode = season.getEpisode();
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
	
	
	
}
