package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.UserAuthDTO;
import com.jonatas.socialnetworkapi.dto.UserCreation;
import com.jonatas.socialnetworkapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	//services

	@Autowired
	private UserService userService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll() {
		return userService.findAll();
	}
	
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return userService.findById(id);
	}
	
	@GetMapping(value = "get/auth")
	public ResponseEntity<Object> auth(@RequestBody UserAuthDTO userAuthDTO){
		return userService.auth(userAuthDTO);
		
	}
	
	@GetMapping(value = "get/workers/{id}")
	public ResponseEntity<Object> getWorkers(@PathVariable String id){
		return userService.getWorkers(id);
	}
	
	@GetMapping(value = "get/evaluations/{id}")
	public ResponseEntity<Object> getEvaluationUser(@PathVariable String id){
		return userService.getEvaluationsUser(id);
	}
	
	//post
	
	@PostMapping(value = "post")
	public ResponseEntity<Object> createUser(@RequestBody UserCreation creationUser){
		return userService.createUser(creationUser);
	}
	

}
