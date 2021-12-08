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
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return entityService.findAll();
	}
	
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return entityService.findById(id);
	}
	
	@GetMapping(value = "get/workers/{id}")
	public ResponseEntity<Object> getWorkers(@PathVariable String id){
		return entityService.getWorkers(id);
	}
	
	@GetMapping(value = "get/seasons/{id}")
	public ResponseEntity<Object> findAllSeasons(@PathVariable String id){
		return entityService.findAllSeasons(id);
	}
	
	@GetMapping(value = "get/evaluations/{id}")
	public ResponseEntity<Object> getEvaluationsEntity(@PathVariable String id){
		return entityService.getEvaluationsEntity(id);
	}
	
	@GetMapping(value = "get/editions/{id}")
	public ResponseEntity<Object> getEditions(@PathVariable String id){
		return entityService.getEditions(id);
	}
	
	//post
	
	@PostMapping(value = "/post/user/{id}")
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

}
