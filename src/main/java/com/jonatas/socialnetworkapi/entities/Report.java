package com.jonatas.socialnetworkapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jonatas.socialnetworkapi.enuns.LevelReport;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypeReport;

@Document
public class Report {
	
	//variables

	@Id
	private String id;
	private TypeObject typeObject = TypeObject.REPORT;
	private LevelReport levelReport;
	private String idReported;
	private TypeReport typeReport;
	private User author;
	private Boolean checked = false;
	private String release;
	
	//variables
		
	public Report() {
		super();
	}

	public Report(LevelReport levelReport, String idReported, TypeReport typeReport, User author,
			String release) {
		super();
		this.levelReport = levelReport;
		this.idReported = idReported;
		this.typeReport = typeReport;
		this.author = author;
		this.release = release;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public LevelReport getLevelReport() {
		return levelReport;
	}

	public void setLevelReport(LevelReport levelReport) {
		this.levelReport = levelReport;
	}

	public String getIdReported() {
		return idReported;
	}

	public void setIdReported(String idReported) {
		this.idReported = idReported;
	}

	public TypeReport getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(TypeReport typeReport) {
		this.typeReport = typeReport;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	} 
	
	
	
}
