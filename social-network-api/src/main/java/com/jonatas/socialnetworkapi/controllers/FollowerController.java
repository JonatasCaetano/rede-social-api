package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.services.FollowerService;

@RestController
@RequestMapping(value = "/followers")
public class FollowerController {

	@Autowired
	private FollowerService followerService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return followerService.findAll();
	}
	
	@GetMapping(value = "get/user/{id}")
	public ResponseEntity<Object> findByUser(@PathVariable String id) {
		return followerService.findByUser(id);
	}
	
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return followerService.findById(id);
	}
	
	@GetMapping(value = "get/followings/all/user/{userId}")
	public ResponseEntity<Object> getAllFollowing(@PathVariable String userId){
		return followerService.getAllFollowing(userId);
	}
	
	@GetMapping(value = "get/followers/all/user/{userId}")
	public ResponseEntity<Object> getAllFollower(@PathVariable String userId){
		return followerService.getAllFollower(userId);
	}
	
	//post
	
	@PostMapping(value = "post/add/follower/{followerId}/following/{followingId}")
	public ResponseEntity<Void> addFollowing(@PathVariable String followerId, @PathVariable String followingId){
		return followerService.addFollowing(followerId, followingId);
	}
	
	@PostMapping(value = "post/remove/follower/{followerId}/following/{followingId}")
	public ResponseEntity<Void> removeFollowing(@PathVariable String followerId, @PathVariable String followingId){
		return followerService.removeFollowing(followerId, followingId);
	}
}
