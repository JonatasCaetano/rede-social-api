package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.services.FollowerService;

@RestController
@RequestMapping(value = "/followers")
public class FollowerController {

	@Autowired
	private FollowerService followerService;
	
	@GetMapping
	public ResponseEntity<List<Follower>> findAll(){
		return followerService.findAll();
	}
	
	@GetMapping(value = "user/{id}")
	public ResponseEntity<Follower> findByUser(@PathVariable String id) {
		return followerService.findByUser(id);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Follower> findById(@PathVariable String id){
		return followerService.findById(id);
	}
	
	@PostMapping(value = "/add/{userId}/{following}")
	public ResponseEntity<Object> addFollowing(@PathVariable String userId, @PathVariable String following){
		return followerService.addFollowing(userId, following);
	}
}
