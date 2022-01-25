package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.dto.UserDTO;
import com.jonatas.socialnetworkapi.enuns.TypeEntity;
import com.jonatas.socialnetworkapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	//services

	@Autowired
	private UserService userService;
	
	//get
	
	@GetMapping(value = "get/users")
	public ResponseEntity<Object> findAllMini() {
		return userService.findAllMini();
	}
	
	@GetMapping(value = "get/user/{id}")
	public ResponseEntity<Object> findByIdMini(@PathVariable String id){
		return userService.findByIdMini(id);
	}
	
	@GetMapping(value = "get/login/email/{email}/password/{password}")
	public ResponseEntity<Object> loginMini(@PathVariable String email, @PathVariable String password){
		return userService.loginMini(email, password);
	}
	
	@GetMapping(value = "get/user/{id}/invitation")
	public ResponseEntity<Object> getInvitationMini(@PathVariable String id){
		return userService.getInvitationMini(id);
	}
	
	@GetMapping(value = "get/user/{id}/workers")
	public ResponseEntity<Object> getWorkersMini(@PathVariable String id){
		return userService.getWorkersMini(id);
	}
	
	/*
	
	@GetMapping(value = "get/user/{id}/posts")
	public ResponseEntity<Object> getPostsMini(@PathVariable String id){
		return userService.getPostsMini(id);
	}
	
	@GetMapping(value = "get/user/{id}/comments")
	public ResponseEntity<Object> getCommentsMini(@PathVariable String id){
		return userService.getCommentsMini(id);
	}
	
	@GetMapping(value = "get/user/{id}/likes")
	public ResponseEntity<Object> getLikesMini(@PathVariable String id){
		return userService.getLikesMini(id);
	}
	
	*/
	
	@GetMapping(value = "get/user/{id}/entitySaves/{typeEntity}")
	public ResponseEntity<Object> getEntitySaves(@PathVariable String id, @PathVariable TypeEntity typeEntity){
		return userService.getEntitySaves(id, typeEntity);
	}
	
	
	@GetMapping(value = "get/check/email/{email}")
	public ResponseEntity<Object> checkEmail(@PathVariable String email){
		return userService.checkEmail(email);
	}
	
	@GetMapping(value = "get/check/name/{name}")
	public ResponseEntity<Object> checkName(@PathVariable String name){
		return userService.checkName(name);
	}
		
	@GetMapping(value = "get/users/name")
	public ResponseEntity<Object> findByName(@RequestParam(value = "name", defaultValue = "") String name){
		return userService.findByName(name);
	}
	
	//post
	
	@PostMapping(value = "post/user")
	public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO){
		return userService.createUser(userDTO);
	}
			
	//put
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(@RequestBody UserDTO userUpdateDTO){
		return userService.updateName(userUpdateDTO);
	}
	
	@PutMapping(value = "put/email")
	public ResponseEntity<Void> updateEmail(@RequestBody UserDTO userUpdateDTO){
		return userService.updateEmail(userUpdateDTO);
	}
	
	@PutMapping(value = "put/password")
	public ResponseEntity<Void> updatePassword(@RequestBody UserDTO userUpdateDTO){
		return userService.updatePassword(userUpdateDTO);
	}
	
	@PutMapping(value = "put/add/image")
	public ResponseEntity<Void> addImage(@RequestBody UserDTO userUpdateDTO){
		return userService.addImage(userUpdateDTO);
	}
	
	@PutMapping(value = "put/remove/image")
	public ResponseEntity<Void> removeImage(@RequestBody UserDTO userUpdateDTO){
		return userService.removeImage(userUpdateDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(@RequestBody UserDTO userUpdateDTO){
		return userService.updateDescription(userUpdateDTO);
	}
		
	@PutMapping(value = "put/place")
	public ResponseEntity<Void> updatePlace(@RequestBody UserDTO userUpdateDTO){
		return userService.updatePlace(userUpdateDTO);
	}
	
	@PutMapping(value = "put/privacy")
	public ResponseEntity<Void> updatePrivacy(@RequestBody UserDTO userUpdateDTO){
		return userService.updatePrivacy(userUpdateDTO);
	}
	
	@PutMapping(value = "put/status")
	public ResponseEntity<Void> updateStatus(@RequestBody UserDTO userUpdateDTO){
		return userService.updateStatus(userUpdateDTO);
	}
	
	@PutMapping(value = "put/checked")
	public ResponseEntity<Void> updateChecked(@RequestBody UserDTO userUpdateDTO){
		return userService.updateChecked(userUpdateDTO);
	}
	

}
