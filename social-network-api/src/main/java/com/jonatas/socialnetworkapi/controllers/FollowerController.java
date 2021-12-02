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
	
	@GetMapping(value = "get/all")
	public ResponseEntity<List<Follower>> findAll(){
		return followerService.findAll();
	}
	
	@GetMapping(value = "get/user/{id}")
	public ResponseEntity<Follower> findByUser(@PathVariable String id) {
		return followerService.findByUser(id);
	}
	
	@GetMapping(value = "get/{id}")
	public ResponseEntity<Follower> findById(@PathVariable String id){
		return followerService.findById(id);
	}
	
	@PostMapping(value = "post/add/{followerId}/{followingId}")
	public ResponseEntity<Void> addFollowing(@PathVariable String followerId, @PathVariable String followingId){
		return followerService.addFollowing(followerId, followingId);
	}
	
	@PostMapping(value = "post/remove/{followerId}/{followingId}")
	public ResponseEntity<Void> removeFollowing(@PathVariable String followerId, @PathVariable String followingId){
		return followerService.removeFollowing(followerId, followingId);
	}
}
