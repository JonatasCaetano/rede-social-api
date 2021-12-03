package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jonatas.socialnetworkapi.dto.EntityDTO;

@Document
public class Entity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String description;
	private String image;
	private String year;
	private int type;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Worker> workers = new ArrayList<>();
	
	public Entity() {
		super();
	}

	public Entity(String name, String description, String image, String year, int type) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.year = year;
		this.type = type;
	}

	public Entity(EntityDTO entityDTO) {
		this.name = entityDTO.getName();
		this.description = entityDTO.getDescription();
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

	public String getId() {
		return id;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return Objects.equals(name, other.name) && type == other.type && Objects.equals(year, other.year);
	}
	
	

	
	
}
