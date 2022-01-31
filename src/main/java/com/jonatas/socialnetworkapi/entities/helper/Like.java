package com.jonatas.socialnetworkapi.entities.helper;

import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class Like {
	
	private String id;
	private TypeObject typeObject;
	
	public Like() {
		super();
	}

	public Like(String id, TypeObject typeObject) {
		super();
		this.id = id;
		this.typeObject = typeObject;
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
	
	
}
