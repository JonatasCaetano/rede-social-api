package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document
public class Invitation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	
	@Id
	private String id;
	
	private Date release;
	
	private String value;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private User user;
	
	@DBRef(lazy = true)
	@JsonManagedReference
	private List<User>  invited = new ArrayList<>();
	
	//builders
	
	public Invitation() {
		
	}

	public Invitation(String id, User user, String value) {
		this.id = id;
		this.user = user;
		this.value = value;
	}
	
	//getters and setters

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getInvited() {
		return invited;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}
	
	//hashCode and equals

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invitation other = (Invitation) obj;
		return Objects.equals(value, other.value);
	}

		
	

}
