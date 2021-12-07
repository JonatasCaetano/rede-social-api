package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Edition;
import com.jonatas.socialnetworkapi.repositories.EditionRepository;

@Service
public class EditionService {

	//repositories
	
	@Autowired
	private EditionRepository editionRepository;
	
	//methods
	
	public ResponseEntity<Edition> newEdition(Edition edition){
		try {
			edition = editionRepository.insert(edition);
			return ResponseEntity.ok().body(edition);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
