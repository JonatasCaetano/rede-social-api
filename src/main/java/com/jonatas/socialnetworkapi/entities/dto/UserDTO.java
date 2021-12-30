package com.jonatas.socialnetworkapi.entities.dto;

import java.util.Date;

public class UserDTO {

	private String idUser;
	private String name;
	private String email;
	private String password;
	private String image;
	private String description;
	private Date birthDate;
	private String place;
	private boolean privacy = false;
	private boolean status = true;
	private boolean checked = false;
	
	public UserDTO() {
		super();
	}

	public UserDTO(String id, String name, String email, String password, String image, String description,
			Date birthDate, String place, boolean privacy, boolean status, boolean checked) {
		super();
		this.idUser = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.image = image;
		this.description = description;
		this.birthDate = birthDate;
		this.place = place;
		this.privacy = privacy;
		this.status = status;
		this.checked = checked;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
