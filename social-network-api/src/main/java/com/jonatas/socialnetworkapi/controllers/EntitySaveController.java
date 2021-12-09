package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.EntitySaveDTO;
import com.jonatas.socialnetworkapi.services.EntitySaveService;

@RestController
@RequestMapping(value = "/entitysaves")
public class EntitySaveController {

	//services
	
	@Autowired
	private EntitySaveService entitySaveService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return entitySaveService.findAll();
	}
		
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return entitySaveService.findById(id);
	}
	
	//post
	
	@PostMapping(value = "post")
	public ResponseEntity<Object> newEntitySave(@RequestBody EntitySaveDTO entitySaveDTO){
		return entitySaveService.newEntitySave(entitySaveDTO);
	}
	
	//put
	@PutMapping(value = "put/id/{id}")
	public String updateEntitySave() {
		return "update entitySave";
	}
	
	//delete
	
	@DeleteMapping(value = "delete")
	public String deleteEntitySave() {
		return "delete entitySave";
	}
}
