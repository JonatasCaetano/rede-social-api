package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;

@Service
public class EntityService {

	@Autowired
	private EntityRepository entityRepository;
	
	public ResponseEntity<List<Entity>> findAll(){
		List<Entity> list = entityRepository.findAll();
		return ResponseEntity.ok().body(list);
	}
}
