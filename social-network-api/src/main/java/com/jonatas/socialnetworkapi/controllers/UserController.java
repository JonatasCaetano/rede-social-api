package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.Auth;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return userService.findAll();
	}
	
	@GetMapping(value = "/auth")
	public ResponseEntity<Object> auth(@RequestBody Auth auth){
		return userService.auth(auth);
		
	}
}
