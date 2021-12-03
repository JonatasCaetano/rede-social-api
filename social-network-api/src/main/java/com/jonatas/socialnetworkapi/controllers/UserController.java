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

import com.jonatas.socialnetworkapi.dto.CreationUser;
import com.jonatas.socialnetworkapi.dto.UserAuthDTO;
import com.jonatas.socialnetworkapi.dto.WorkerUserDTO;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<List<User>> findAll() {
		return userService.findAll();
	}
	
	@GetMapping(value = "get/auth")
	public ResponseEntity<Object> auth(@RequestBody UserAuthDTO userAuthDTO){
		return userService.auth(userAuthDTO);
		
	}
	
	@GetMapping(value = "get/workers/{id}")
	public ResponseEntity<List<WorkerUserDTO>> getWorkers(@PathVariable String id){
		return userService.getWorkers(id);
	}
	
	//post
	
	@PostMapping(value = "post/user")
	public ResponseEntity<CreationUser> saveUser(@RequestBody CreationUser creationUser){
		return userService.saveUser(new User(creationUser), creationUser.getInvitation());
	}
}
