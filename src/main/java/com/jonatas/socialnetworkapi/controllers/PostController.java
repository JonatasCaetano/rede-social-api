package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.dto.PostTalkDTO;
import com.jonatas.socialnetworkapi.entities.dto.PostUpdateDTO;
import com.jonatas.socialnetworkapi.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	//get
	
	@GetMapping(value = "/get/posts")
	public ResponseEntity<Object> findAllMini(){
		return postService.findAllMini();
	}
	
	@GetMapping(value = "/get/post/{idPost}/user/{idUser}")
	public ResponseEntity<Object> findByIdMini(@PathVariable String idPost, @PathVariable String idUser){
		return postService.findByIdMini(idPost, idUser);
	}
	
	@GetMapping(value = "/get/user/{id}/all")
	public ResponseEntity<Object> getPostAll(@PathVariable String id){
		return postService.getPostAll(id);
	}
		
	@GetMapping(value = "/get/post/{idPost}/comments/user/{idUser}")
	public ResponseEntity<Object> getCommentsMini(@PathVariable String idPost, @PathVariable String idUser){
		return postService.getCommentsMini(idPost, idUser);
	}
	
	@GetMapping(value = "get/post/{id}/likes")
	public ResponseEntity<Object> getLikes(@PathVariable String id){
		return postService.getLikes(id);
	}
	
	//post
	
	@PostMapping(value = "post/update")
	public ResponseEntity<Object> newPostUpdate(@RequestBody PostUpdateDTO postUpdateDTO){
		return postService.newPostUpdate(postUpdateDTO);
	}
	
	@PostMapping(value = "post/talk")
	public ResponseEntity<Object> newPostTalk(@RequestBody PostTalkDTO PostTalkDTO){
		return postService.newPostTalk(PostTalkDTO);
	}
	
	//put
	
	@PutMapping(value = "put/like/post/{idPost}/user/{idUser}")
	public ResponseEntity<Object> addLike(@PathVariable String idUser, @PathVariable String idPost){
		return postService.addLike(idUser, idPost);
	}
	
	@PutMapping(value = "put/body")
	public ResponseEntity<Object> addBodyUpdatePost(@RequestBody PostUpdateDTO postUpdateDTO){
		return postService.addBodyUpdatePost(postUpdateDTO);
	}
		
	//delete
	
	@DeleteMapping(value = "delete/post/{idPost}/user/{idUser}")
	public ResponseEntity<Object> deletePost(@PathVariable String idUser, @PathVariable String idPost){
		return postService.deletePost(idPost, idUser);
	}
}
