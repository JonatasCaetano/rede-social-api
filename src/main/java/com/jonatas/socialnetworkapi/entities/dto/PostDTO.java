package com.jonatas.socialnetworkapi.entities.dto;

import java.util.Date;

public class PostDTO {

	private String idPost;
	private int typeEntity = 1;
	private Date release;
	private String body;
	private int category;
	

	private String idUser;
	private String idEntity;
	private String idSeason;
	private String isEpisode;
	
	public PostDTO() {
		super();
	}

	public PostDTO(int typeEntity, Date release, String body, int category, String user, String entity, String season,
			String episode) {
		super();
		this.typeEntity = typeEntity;
		this.release = release;
		this.body = body;
		this.category = category;
		this.idUser = user;
		this.idEntity = entity;
		this.idSeason = season;
		this.isEpisode = episode;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public int getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(int typeEntity) {
		this.typeEntity = typeEntity;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	public String getIdSeason() {
		return idSeason;
	}

	public void setIdSeason(String idSeason) {
		this.idSeason = idSeason;
	}

	public String getIsEpisode() {
		return isEpisode;
	}

	public void setIsEpisode(String isEpisode) {
		this.isEpisode = isEpisode;
	}
}
