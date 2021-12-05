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
import com.jonatas.socialnetworkapi.dto.SeasonDTO;

@Document
public class Season implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	@Id
	private String id;
	private String name;
	private String image;
	private String description;
	private Date release;
	private int number;
	private int episode = 0;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private Entity entity;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Episode> episodes = new ArrayList<>(); 
	
	//builders
	
	public Season() {
		super();
	}

	public Season(String name, String image, String description, Date release, int number, Entity entity) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
		this.release = release;
		this.number = number;
		this.entity = entity;
	}
	
	public Season(SeasonDTO seasonDTO) {
		super();
		this.name = seasonDTO.getName();
		this.image = seasonDTO.getImage();
		this.description = seasonDTO.getDescription();
		this.release = seasonDTO.getRelease();
		this.number = seasonDTO.getNumber();
	}
	
	//getters and setters
	
	public String getId() {
		return id;
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

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}
	
	public List<Episode> getEpisodes() {
		return episodes;
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
		Season other = (Season) obj;
		return number == other.number;
	}

	
	

}
