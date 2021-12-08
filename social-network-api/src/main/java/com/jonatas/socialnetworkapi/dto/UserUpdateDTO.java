package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

public class UserUpdateDTO {

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
	
	public UserUpdateDTO() {
		super();
	}

	public UserUpdateDTO(String id, String name, String email, String password, String image, String description,
			Date birthDate, String city, boolean privacy, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.image = image;
		this.description = description;
		this.birthDate = birthDate;
		this.city = city;
		this.privacy = privacy;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
		
	
}
