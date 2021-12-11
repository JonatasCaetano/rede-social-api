package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.PostDTO;
import com.jonatas.socialnetworkapi.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	//get
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<Object> findAll(){
		return postService.findAll();
	}
	
	@GetMapping(value = "/get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return postService.findById(id);
	}
	
	//post
	
	@PostMapping(value = "post")
	public ResponseEntity<Object> newPost(@RequestBody PostDTO postDTO){
		return postService.newPost(postDTO);
	}
		
	//delete
	
	@DeleteMapping(value = "delete")
	public ResponseEntity<Object> deletePost(PostDTO postDTO){
		return postService.deletePost(postDTO);
	}
}
