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

import com.jonatas.socialnetworkapi.dto.CommentDTO;
import com.jonatas.socialnetworkapi.services.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	//services
	
	@Autowired
	private CommentService commentService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return commentService.findAll();
	}
	
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return commentService.findById(id);
	}
	
	//post
	@PostMapping(value = "post")
	public ResponseEntity<Object> newComment(@RequestBody CommentDTO commentDTO){
		return commentService.newComment(commentDTO);
	}
	
	//delete
	@DeleteMapping(value = "delete")
	public ResponseEntity<Object> deleteComment(@RequestBody CommentDTO commentDTO){
		return commentService.deleteComment(commentDTO);
	}
	
}
