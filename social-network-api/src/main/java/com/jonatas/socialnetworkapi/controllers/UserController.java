package com.jonatas.socialnetworkapi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.UserAuthDTO;
import com.jonatas.socialnetworkapi.dto.UserCreationDTO;
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
	
	@GetMapping(value = "get/login")
	public ResponseEntity<Object> login(@RequestBody UserAuthDTO userAuthDTO){
		return userService.login(userAuthDTO);
		
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
	public ResponseEntity<Object> createUser(@RequestBody UserCreationDTO creationUser){
		return userService.createUser(creationUser);
	}
	
	//put
	
	@PutMapping(value = "put/{id}/name")
	public ResponseEntity<Void> updateName(@RequestBody String name, @PathVariable String id){
		return userService.updateName(name, id);
	}
	
	@PutMapping(value = "put/{id}/email")
	public ResponseEntity<Void> updateEmail(@RequestBody String email, @PathVariable String id){
		return userService.updateEmail(email, id);
	}
	
	@PutMapping(value = "put/{id}/password")
	public ResponseEntity<Void> updatePassword(@RequestBody String password, @PathVariable String id){
		return userService.updatePassword(password, id);
	}
	
	@PutMapping(value = "put/{id}/image")
	public ResponseEntity<Void> updateImage(@RequestBody String image, @PathVariable String id){
		return userService.updateImage(image, id);
	}
	
	@PutMapping(value = "put/{id}/description")
	public ResponseEntity<Void> updateDescription(@RequestBody String description, @PathVariable String id){
		return userService.updateDescription(description, id);
	}
	
	@PutMapping(value = "put/{id}/birthDate")
	public ResponseEntity<Void> updateBirthDate(@RequestBody Date birthDate, @PathVariable String id){
		return userService.updateBirthDate(birthDate, id);
	}
	
	@PutMapping(value = "put/{id}/city")
	public ResponseEntity<Void> updateCity(@RequestBody String city, @PathVariable String id){
		return userService.updateCity(city, id);
	}
	
	@PutMapping(value = "put/{id}/privacy")
	public ResponseEntity<Void> updatePrivacy(@RequestBody boolean privacy, @PathVariable String id){
		return userService.updatePrivacy(privacy, id);
	}
	

}
