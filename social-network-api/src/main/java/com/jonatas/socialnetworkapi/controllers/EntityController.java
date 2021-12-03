package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.EntityMiniDTO;
import com.jonatas.socialnetworkapi.dto.SeasonEntityDTO;
import com.jonatas.socialnetworkapi.dto.WorkerEntityDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.services.EntityService;

@RestController
@RequestMapping(value = "/entities")
public class EntityController {

	@Autowired
	private EntityService entityService;
	
	//get methods
	
	@GetMapping(value = "get/all")
	public ResponseEntity<List<Entity>> findAll(){
		return entityService.findAll();
	}
	
	@GetMapping(value = "get/workers/{id}")
	public ResponseEntity<List<WorkerEntityDTO>> getWorkers(@PathVariable String id){
		return entityService.getWorkers(id);
	}
	
	@GetMapping(value = "get/seasons/{id}")
	public ResponseEntity<List<SeasonEntityDTO>> findAllSeasons(@PathVariable String id){
		return entityService.findAllSeasons(id);
	}
	
	//post methods
	
	@PostMapping(value = "/post/{id}")
	public ResponseEntity<Entity> saveEntity(@RequestBody EntityMiniDTO entityMiniDTO, @PathVariable String id){
		return entityService.saveEntity(new Entity(entityMiniDTO), id);
	}
}
