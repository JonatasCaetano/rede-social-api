 package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.EditionDTO;
import com.jonatas.socialnetworkapi.dto.mini.EntityMiniDTO;
import com.jonatas.socialnetworkapi.services.EntityService;

@RestController
@RequestMapping(value = "/entities")
public class EntityController {

	@Autowired
	private EntityService entityService;
	
	//get
	
	@GetMapping(value = "get/entities")
	public ResponseEntity<Object> findAllMini(){
		return entityService.findAllMini();
	}
	
	@GetMapping(value = "get/entity/{id}")
	public ResponseEntity<Object> findByIdMini(@PathVariable String id){
		return entityService.findByIdMini(id);
	}
	
	@GetMapping(value = "get/entity/{id}/workers")
	public ResponseEntity<Object> getWorkersMini(@PathVariable String id){
		return entityService.getWorkersMini(id);
	}
	
	@GetMapping(value = "get/entity/{id}/seasons")
	public ResponseEntity<Object> getSeasonsMini(@PathVariable String id){
		return entityService.getSeasonsMini(id);
	}
	
	@GetMapping(value = "get/entity/{id}/evaluations")
	public ResponseEntity<Object> getEvaluationsMini(@PathVariable String id){
		return entityService.getEvaluationsMini(id);
	}
	
	@GetMapping(value = "get/entity/{id}/editions")
	public ResponseEntity<Object> getEditionsMini(@PathVariable String id){
		return entityService.getEditionsMini(id);
	}
	
	//post
	
	@PostMapping(value = "/post/entity/user/{id}")
	public ResponseEntity<Object> createEntity(@RequestBody EntityMiniDTO entityMiniDTO, @PathVariable String id){
		return entityService.createEntity(entityMiniDTO, id);
	}
	
	//put
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(@RequestBody EditionDTO editionDTO){
		return entityService.updateName(editionDTO);
	}
	
	@PutMapping(value = "put/image")
	public ResponseEntity<Void> updateImage(@RequestBody EditionDTO editionDTO){
		return entityService.updateImage(editionDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(@RequestBody EditionDTO editionDTO){
		return entityService.updateDescription(editionDTO);
	}
	
	@PutMapping(value = "put/release")
	public ResponseEntity<Void> updateRelease(@RequestBody EditionDTO editionDTO){
		return entityService.updateRelease(editionDTO);
	}
	
	@PutMapping(value = "put/genre")
	public ResponseEntity<Void> updateGenre(@RequestBody EditionDTO editionDTO){
		return entityService.updateGenre(editionDTO);
	}

}
