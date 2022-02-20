package com.jonatas.socialnetworkapi.entities.dto;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class PostQuestDTO {

	private String idPost;
	private String release;
	private TypePostVisibility typePostVisibility;
	private Boolean spoiler;
	private String idAuthor;
	private String body;
	private List<String> options = new ArrayList<>();
	private List<Integer> votes = new ArrayList<>();
		
	public PostQuestDTO() {
		super();
	}
	
	public PostQuestDTO(String idPost, String release, TypePostVisibility typePostVisibility,
			Boolean spoiler, String idAuthor, String body, List<String> options, List<Integer> votes) {
		super();
		this.idPost = idPost;
		this.release = release;
		this.typePostVisibility = typePostVisibility;
		this.spoiler = spoiler;
		this.idAuthor = idAuthor;
		this.body = body;
		this.options = options;
		this.votes = votes;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<Integer> getVotes() {
		return votes;
	}

	public void setVotes(List<Integer> votes) {
		this.votes = votes;
	}
	
	


}
