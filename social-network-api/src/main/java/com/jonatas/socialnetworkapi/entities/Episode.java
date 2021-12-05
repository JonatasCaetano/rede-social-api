package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.dto.EpisodeDTO;

@Document
public class Episode implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	@Id
	private String id;
	private String name;
	private String description;
	private String image;
	private Date release;
	private int number;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Season season;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Evaluation> evaluation = new ArrayList<>();

	//builders
	
	public Episode() {
		super();
	}

	public Episode(String id, String name, String description, String image, Date release, int number, Season season) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.release = release;
		this.number = number;
		this.season = season;
	}
	
	public Episode(EpisodeDTO episodeDTO) {
		super();
		this.name = episodeDTO.getName();
		this.description = episodeDTO.getDescription();
		this.image = episodeDTO.getImage();
		this.release = episodeDTO.getRelease();
		this.number = episodeDTO.getNumber();
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

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Evaluation> getEvaluation() {
		return evaluation;
	}
	
	//hashCode and equals

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode other = (Episode) obj;
		return number == other.number;
	}
	
	
	
	

}
