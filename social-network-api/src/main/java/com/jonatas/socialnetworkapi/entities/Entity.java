package com.jonatas.socialnetworkapi.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Entity {

	@Id
	private String id;
	private String name;
	private String description;
	
	@DBRef(lazy = true)
	private List<Worker> workers = new ArrayList<>();
	
	public Entity() {
	}

	public Entity(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	
	
}
