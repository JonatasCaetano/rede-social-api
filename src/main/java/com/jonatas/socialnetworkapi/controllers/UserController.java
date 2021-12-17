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
	
	@GetMapping(value = "get/user/{id}/posts")
	public ResponseEntity<Object> getPostsMini(@PathVariable String id){
		return userService.getPostsMini(id);
	}
	
	@GetMapping(value = "get/user/{id}/comments")
	public ResponseEntity<Object> getCommentsMini(@PathVariable String id){
		return userService.getCommentsMini(id);
	}
	
	@GetMapping(value = "get/user/{id}/evaluations")
	public ResponseEntity<Object> getEvaluationMini(@PathVariable String id){
		return userService.getEvaluationsMini(id);
	}
	
	@GetMapping(value = "get/user/{id}/likes")
	public ResponseEntity<Object> getLikesMini(@PathVariable String id){
		return userService.getLikesMini(id);
	}
	
	@GetMapping(value = "post/check/email/{email}")
	public ResponseEntity<Object> checkEmail(@PathVariable String email){
		return userService.checkEmail(email);
	}
	
	@GetMapping(value = "post/check/name/{name}")
	public ResponseEntity<Object> checkName(@PathVariable String name){
		return userService.checkName(name);
	}
		
	//post
	
	@PostMapping(value = "post/user")
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
