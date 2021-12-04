package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Episode;

public class EpisodeDTO {

	private String name;
	private String image;
	private Date release;
	private int number;
	
	public EpisodeDTO() {
		super();
	}

	public EpisodeDTO(String name, String image, Date release, int number) {
		super();
		this.name = name;
		this.image = image;
		this.release = release;
		this.number = number;
	}
	
	public EpisodeDTO(Episode episode) {
		super();
		this.name = episode.getName();
		this.image = episode.getImage();
		this.release = episode.getRelease();
		this.number = episode.getNumber();
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

	
}
