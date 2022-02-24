package com.jonatas.socialnetworkapi.entities.dto;

import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class PostTalkDTO {
	
	private String idPost;
	private String release;
	private String body;
	private TypePostVisibility typePostVisibility;
	private Boolean spoiler;
	private String idAuthor;
		
	public PostTalkDTO() {
		super();
	}

	public PostTalkDTO(String idPost, String release, String body, TypePostVisibility typePostVisibility,
			Boolean spoiler, String idAuthor) {
		super();
		this.idPost = idPost;
		this.release = release;
		this.body = body;
		this.typePostVisibility = typePostVisibility;
		this.spoiler = spoiler;
		this.idAuthor = idAuthor;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public TypePostVisibility getTypePostVisibility() {
		return typePostVisibility;
	}

	public void setTypePostVisibility(TypePostVisibility typePostVisibility) {
		this.typePostVisibility = typePostVisibility;
	}

	public Boolean getSpoiler() {
		return spoiler;
	}

	public void setSpoiler(Boolean spoiler) {
		this.spoiler = spoiler;
	}

	public String getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(String idAuthor) {
		this.idAuthor = idAuthor;
	}

	
	
	
}