package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.Level;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class EntitySave implements Serializable{
	private static final long serialVersionUID = 1L;

	//variables
	
	@Id
	private String id;
	
	private Level level;
	private int category;
	private boolean goal = false;
	private boolean rated = false;
	private boolean reviewed = false;
	private int evaluation;
	private String review;
	private TypeObject typeObject = TypeObject.ENTITY_SAVE;
	private boolean spoiler;
	private String release;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	
	@JsonManagedReference
	@DocumentReference(collection = "user")
	private User user;
	
	@JsonManagedReference
	@DocumentReference(collection = "entity")
	private Entity entity;
	
	@JsonManagedReference
	@DocumentReference(collection = "season")
	private Season season;
	
	@JsonManagedReference
	@DocumentReference(collection = "episode")
	private Episode episode;
	
	@JsonBackReference
	@DocumentReference(collection = "comment")
	private List<Comment> comments = new ArrayList<>();
	
	@JsonBackReference
	@DocumentReference(collection = "user")
	private List<User> likes = new ArrayList<>();
	
	//variables

	public EntitySave() {
		super();
	}

	public EntitySave(User user, Entity entity, Season season, Episode episode, int category, Level level, Boolean spoiler) {
		super();
		this.user = user;
		this.entity = entity;
		this.season = season;
		this.episode = episode;
		this.category = category;
		this.level = level;
		this.spoiler = spoiler;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public boolean isGoal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public String getId() {
		return id;
	}
	
	public TypeObject getTypeObject() {
		return typeObject;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Boolean getSpoiler() {
		return spoiler;
	}

	public void setSpoiler(Boolean spoiler) {
		this.spoiler = spoiler;
	}
	
	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
	public int getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(int likeQuantity) {
		this.likeQuantity += likeQuantity;
	}

	public int getCommentQuantity() {
		return commentQuantity;
	}

	public void setCommentQuantity(int commentQuantity) {
		this.commentQuantity += commentQuantity;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<User> getLikes() {
		return likes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntitySave other = (EntitySave) obj;
		return Objects.equals(id, other.id);
	}	
	
}
