package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.repositories.EntitySaveRepository;

@Service
public class EntitySaveService {

	//repositories
	
	@Autowired
	private EntitySaveRepository entitySaveRepository;

	//methods
	
	public ResponseEntity<Object> findAll(){
		try {
			List<EntitySave> list = entitySaveRepository.findAll();
			return ResponseEntity.ok(list);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
