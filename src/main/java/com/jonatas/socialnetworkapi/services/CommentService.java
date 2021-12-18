package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Comment;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.CommentDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.CommentMiniDTO;
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
	
	public ResponseEntity<Object> findAllMini(){
		try {
			List<Comment> comments = commentRepository.findAll();
			List<CommentMiniDTO> commentMiniDTOs = new ArrayList<>();
			for(Comment comment : comments) {
				CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
				commentMiniDTOs.add(commentMiniDTO);
			}
			return ResponseEntity.ok().body(commentMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Comment comment = commentRepository.findById(id).get();
			CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
			return ResponseEntity.ok().body(commentMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
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
			CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
			return ResponseEntity.created(null).body(commentMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//delete
	
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
	
	//internal
	
	public ResponseEntity<Object> findById(String id){
		try {
			Comment comment = commentRepository.findById(id).get();
			return ResponseEntity.ok().body(comment);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
}
