package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.services.EntityService;

@RestController
@RequestMapping(value = "/entities")
public class EntityController {

	@Autowired
	private EntityService entityService;
	
	@GetMapping
	public ResponseEntity<List<Entity>> findAll(){
		return entityService.findAll();
	}
	
	@GetMapping(value = "/{id}/workers")
	public ResponseEntity<List<Worker>> getWorkers(@PathVariable String id){
		return entityService.getWorkers(id);
	}
}
