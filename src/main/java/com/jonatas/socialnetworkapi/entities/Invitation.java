package com.jonatas.socialnetworkapi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

@Document
public class Invitation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//variables
	
	@Id
	private String id;
	
	private Date release;
	private String value;
	private TypeObject typeObject = TypeObject.INVITATION;

	@JsonManagedReference
	@DocumentReference(collection = "user")
	private User user;
	
	@JsonManagedReference
	@DocumentReference(collection = "user")
	private List<User>  invited = new ArrayList<>();
	
	//variables
	
	public Invitation() {
	}

	public Invitation(String id, User user, String value) {
		this.id = id;
		this.user = user;
		this.value = value;
	}
	
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

	public TypeObject getTypeObject() {
		return typeObject;
	}

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
