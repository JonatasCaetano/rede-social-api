package com.jonatas.socialnetworkapi.entities.dto;

import java.util.Date;

public class EpisodeDTO {

	private String idEpisode;
	private String name;
	private String description;
	private int numberEpisode;
	
	public EpisodeDTO() {
		super();
	}

	public EpisodeDTO(String name, String description, String image, Date release, int numberEpisode) {
		super();
		this.name = name;
		this.description = description;
		this.numberEpisode = numberEpisode;
	}

	public String getIdEpisode() {
		return idEpisode;
	}

	public void setIdEpisode(String idEpisode) {
		this.idEpisode = idEpisode;
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

	public int getNumberEpisode() {
		return numberEpisode;
	}

	public void setNumberEpisode(int numberEpisode) {
		this.numberEpisode = numberEpisode;
	}
		
	
	
}
