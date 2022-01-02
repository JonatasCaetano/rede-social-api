package com.jonatas.socialnetworkapi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypeTalk;

public class Talk {

	private String id;
	private Date release;
	private String title;
	private String body;
	private TypeTalk typeTalk;
	private TypeObject typeObject = TypeObject.TALK;
	List<String> images = new ArrayList<>();
	private int LikeQuantity = 0;
	@DBRef(lazy = true)
	@JsonBackReference
	private Entity entity;
	@DBRef(lazy = true)
	@JsonBackReference
	private Season season;
	@DBRef(lazy = true)
	@JsonBackReference
	private Episode episode;
	@DBRef(lazy = true)
	@JsonBackReference
	List<Comment> comments = new ArrayList<>();
	@DBRef(lazy = true)
	@JsonBackReference
	List<User> likes = new ArrayList<>();
	
	
}
