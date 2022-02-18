package com.jonatas.socialnetworkapi.entities.dto;

import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class PostTalkDTO {
	
	private String id;
	private String release;
	private String body;
	private TypePost typePost;
	private TypePostVisibility typePostVisibility;
	private Boolean spoiler;
	private String idAuthor;
		
	public PostTalkDTO() {
		super();
	}

	public PostTalkDTO(String id, String release, String body, TypePost typePost, TypePostVisibility typePostVisibility,
			Boolean spoiler, String idAuthor) {
		super();
		this.id = id;
		this.release = release;
		this.body = body;
		this.typePost = typePost;
		this.typePostVisibility = typePostVisibility;
		this.spoiler = spoiler;
		this.idAuthor = idAuthor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public TypePost getTypePost() {
		return typePost;
	}

	public void setTypePost(TypePost typePost) {
		this.typePost = typePost;
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
