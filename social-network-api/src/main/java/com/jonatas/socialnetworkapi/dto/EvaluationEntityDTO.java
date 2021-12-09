package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

import com.jonatas.socialnetworkapi.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.entities.Evaluation;

public class EvaluationEntityDTO {

	//attributes
	
	private String id;
	private int type;
	private double value;
	private Date release;
	private UserMiniDTO user;
	
	
	//builders
	
	public EvaluationEntityDTO() {
		super();
	}

	public EvaluationEntityDTO(String id, UserMiniDTO user, double value, Date release, int type) {
		super();
		this.id = id;
		this.user = user;
		this.value = value;
		this.release = release;
		this.setType(type);
	}
	
	public EvaluationEntityDTO(Evaluation evaluation) {
		this.id = evaluation.getId();
		this.user = evaluation.getUser() != null ? new UserMiniDTO(evaluation.getUser()) : null;
		this.value = evaluation.getValue();
		this.release = evaluation.getRelease();
		this.setType(evaluation.getType());
	}

	//getters and setters
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
