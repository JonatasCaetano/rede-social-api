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

import com.jonatas.socialnetworkapi.dto.AuthDTO;
import com.jonatas.socialnetworkapi.dto.UserDTO;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "get/all")
	public ResponseEntity<List<User>> findAll() {
		return userService.findAll();
	}
	
	@GetMapping(value = "get/auth")
	public ResponseEntity<Object> auth(@RequestBody AuthDTO auth){
		return userService.auth(auth);
		
	}
	
	@GetMapping(value = "get/workers/{id}")
	public ResponseEntity<List<Worker>> getWorkers(@PathVariable String id){
		return userService.getWorkers(id);
	}
	
	@PostMapping(value = "post/user")
	public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO){
		return userService.saveUser(new User(userDTO));
	}
}
