package com.jonatas.socialnetworkapi.dto;

import java.util.Date;

import com.jonatas.socialnetworkapi.entities.Evaluation;

public class EvaluationDTO {
	
	private String idEvaluation;
	private String idUser;
	private String idEntity;
	private String idSeason;
	private String idEpisode;
	private double value;
	private Date release;
	private int type;

	public EvaluationDTO() {
		super();
	}

	public EvaluationDTO(String idUser, String idEntity, String idSeason, String idEpisode,
			double value, Date release, int type) {
		super();
		this.idUser = idUser;
		this.idEntity = idEntity;
		this.idSeason = idSeason;
		this.idEpisode = idEpisode;
		this.value = value;
		this.release = release;
		this.type = type;
	}

	public EvaluationDTO(Evaluation evaluation) {
		super();
		this.idUser = evaluation.getUser().getId() != null ? evaluation.getUser().getId() : null;
		this.idEntity = evaluation.getEntity().getId() != null ? evaluation.getEntity().getId() : null;
		this.idSeason = evaluation.getSeason().getId() != null ? evaluation.getSeason().getId() : null;
		this.idEpisode = evaluation.getEpisode().getId() != null ? evaluation.getEpisode().getId() : null;
		this.value = evaluation.getValue();
		this.release = evaluation.getRelease();
		this.type = evaluation.getType();
	}

	public String getIdEvaluation() {
		return idEvaluation;
	}

	public void setIdEvaluation(String idEvaluation) {
		this.idEvaluation = idEvaluation;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	public String getIdSeason() {
		return idSeason;
	}

	public void setIdSeason(String idSeason) {
		this.idSeason = idSeason;
	}

	public String getIdEpisode() {
		return idEpisode;
	}

	public void setIdEpisode(String idEpisode) {
		this.idEpisode = idEpisode;
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
