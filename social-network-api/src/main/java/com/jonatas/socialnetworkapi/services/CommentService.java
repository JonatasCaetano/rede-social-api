package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.CommentDTO;
import com.jonatas.socialnetworkapi.entities.Comment;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.CommentRepository;

@Service
public class CommentService {

	//repositories
	
	@Autowired
	private CommentRepository commentRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private PostService postService;
	
	//methods
	
	public ResponseEntity<Object> findAll(){
		try {
			List<Comment> comments = commentRepository.findAll();
			return ResponseEntity.ok().body(comments);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			Comment comment = commentRepository.findById(id).get();
			return ResponseEntity.ok().body(comment);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newComment(CommentDTO commentDTO){
		try {
			User user = (User) userService.findById(commentDTO.getUser()).getBody();
			Post post = (Post) postService.findById(commentDTO.getPost()).getBody();
			Comment comment = new Comment(commentDTO.getRelease(), commentDTO.getBody(), user, post);
			comment = commentRepository.insert(comment);
			user.getComments().add(comment);
			userService.save(user);
			post.getComments().add(comment);
			postService.save(post);
			return ResponseEntity.created(null).body(comment);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> deleteComment(CommentDTO commentDTO){
		try {
			User user = (User) userService.findById(commentDTO.getUser()).getBody();
			Post post = (Post) postService.findById(commentDTO.getPost()).getBody();
			Comment comment = commentRepository.findById(commentDTO.getId()).get();
			user.getComments().remove(comment);
			userService.save(user);
			post.getComments().remove(comment);
			postService.save(post);
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
