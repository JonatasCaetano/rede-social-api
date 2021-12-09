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
import com.jonatas.socialnetworkapi.dto.UserCreationDTO;

@Document
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
		
	@Id
	private String id;
	
	private String name;
	private String email;
	private String password;
	private String image;
	private String description;
	private Date birthDate;
	private String city;
	private boolean privacy = false;
	private boolean status = true;
	
	private boolean checked = false;
	private int following = 0;
	private int followers = 0;
	private Date release;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private Follower follower;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private Invitation invitation;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Worker> workers = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<Evaluation> evaluations = new ArrayList<>();
	
	@DBRef(lazy = true)
	@JsonBackReference
	private List<EntitySave> entitySaves = new ArrayList<>();
	
	//builders
	
	public User() {
		super();
	}
	
	public User(String name, String email, String password, String image, String description, Date release, String city) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.image = image;
		this.description = description;
		this.release = release;
		this.city = city;
	}
		
	//getters and setters

	public User(UserCreationDTO userCreation) {
		super();
		this.name = userCreation.getName();
		this.email = userCreation.getEmail();
		this.password = userCreation.getPassword();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public Follower getFollower() {
		return follower;
	}

	public void setFollower(Follower follower) {
		this.follower = follower;
	}

	public Invitation getInvitation() {
		return invitation;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following += following;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers += followers;
	}
	
	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}
		
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}
		
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<EntitySave> getEntitySaves() {
		return entitySaves;
	}
	
	//hashCode and equals

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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", image=" + image
				+ ", description=" + description + ", status=" + status + ", privacy=" + privacy + ", checked="
				+ checked + ", following=" + following + ", followers=" + followers + ", release=" + release
				+ ", follower=" + follower + ", workers=" + workers + ", invitation=" + invitation + "]";
	}
	
	
	

}
