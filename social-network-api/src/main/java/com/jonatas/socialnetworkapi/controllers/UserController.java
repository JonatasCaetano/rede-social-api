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

import com.jonatas.socialnetworkapi.dto.UserAuthDTO;
import com.jonatas.socialnetworkapi.dto.UserCreationDTO;
import com.jonatas.socialnetworkapi.dto.UserUpdateDTO;
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
	
	@GetMapping(value = "get/posts/{id}")
	public ResponseEntity<Object> getPosts(@PathVariable String id){
		return userService.getPosts(id);
	}
	
	@GetMapping(value = "get/comments/{id}")
	public ResponseEntity<Object> getComments(@PathVariable String id){
		return userService.getComments(id);
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
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updateName(userUpdateDTO);
	}
	
	@PutMapping(value = "put/email")
	public ResponseEntity<Void> updateEmail(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updateEmail(userUpdateDTO);
	}
	
	@PutMapping(value = "put/password")
	public ResponseEntity<Void> updatePassword(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updatePassword(userUpdateDTO);
	}
	
	@PutMapping(value = "put/image")
	public ResponseEntity<Void> updateImage(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updateImage(userUpdateDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updateDescription(userUpdateDTO);
	}
	
	@PutMapping(value = "put/birthDate")
	public ResponseEntity<Void> updateBirthDate(UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updateBirthDate(userUpdateDTO);
	}
	
	@PutMapping(value = "put/city")
	public ResponseEntity<Void> updateCity(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updateCity(userUpdateDTO);
	}
	
	@PutMapping(value = "put/privacy")
	public ResponseEntity<Void> updatePrivacy(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updatePrivacy(userUpdateDTO);
	}
	
	@PutMapping(value = "put/status")
	public ResponseEntity<Void> updateStatus(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id){
		return userService.updateStatus(userUpdateDTO);
	}
	

}
