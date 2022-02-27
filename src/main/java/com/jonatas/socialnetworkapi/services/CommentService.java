package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Comment;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.CommentDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.CommentMiniDTO;
import com.jonatas.socialnetworkapi.entities.helper.LikeUser;
import com.jonatas.socialnetworkapi.enuns.TypeComment;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
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
	
	@Autowired
	@Lazy
	private EntitySaveService entitySaveService;
	
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

			User user = (User) userService.findById(commentDTO.getIdAuthor()).getBody();
			Post post = (Post) postService.findById(commentDTO.getIdPost()).getBody();
			Comment comment = new Comment(commentDTO.getRelease(), commentDTO.getBody(), user, post, null, TypeComment.POST);
			comment = commentRepository.insert(comment);
			user.getComments().add(comment);
			userService.save(user);
			post.getComments().add(comment);
			post.setCommentQuantity(1);
			postService.save(post);
			CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
			return ResponseEntity.created(null).body(commentMiniDTO);
		}catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> newCommentEntitySave(CommentDTO commentDTO){
		try {

			User user = (User) userService.findById(commentDTO.getIdAuthor()).getBody();
			EntitySave entitySave = (EntitySave) entitySaveService.findById(commentDTO.getIdEntitySave()).getBody();
			Comment comment = new Comment(commentDTO.getRelease(), commentDTO.getBody(), user, null, entitySave, TypeComment.ENTITY_SAVE);
			comment = commentRepository.insert(comment);
			user.getComments().add(comment);
			userService.save(user);
			entitySave.getComments().add(comment);
			entitySave.setCommentQuantity(1);
			entitySaveService.save(entitySave);
			CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
			return ResponseEntity.created(null).body(commentMiniDTO);
		}catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	//delete
	
	public ResponseEntity<Object> deleteComment(CommentDTO commentDTO){
		try {
//			System.out.println(commentDTO.getIdComment());
//			System.out.println(commentDTO.getIdPost());
//			System.out.println(commentDTO.getIdAuthor());
			User user = (User) userService.findById(commentDTO.getIdAuthor()).getBody();
			Post post = (Post) postService.findById(commentDTO.getIdPost()).getBody();
			Comment comment = commentRepository.findById(commentDTO.getIdComment()).get();
			
			if(user.getId().hashCode() != comment.getAuthor().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			
			user.getComments().remove(comment);
			userService.save(user);
			post.getComments().remove(comment);
			post.setCommentQuantity(-1);
			postService.save(post);
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> deleteCommentEntitySave(CommentDTO commentDTO){
		try {
			User user = (User) userService.findById(commentDTO.getIdAuthor()).getBody();
			EntitySave entitySave = (EntitySave) entitySaveService.findById(commentDTO.getIdEntitySave()).getBody();
			Comment comment = commentRepository.findById(commentDTO.getIdComment()).get();
			
			if(user.getId().hashCode() != comment.getAuthor().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			
			user.getComments().remove(comment);
			userService.save(user);
			entitySave.getComments().remove(comment);
			entitySave.setCommentQuantity(-1);
			entitySaveService.save(entitySave);
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	//put
	
		public ResponseEntity<Object> addLike(String idUser, String idComment){
			try {
				User user = (User) userService.findById(idUser).getBody();
				Comment comment = commentRepository.findById(idComment).get();
				List<User> users = comment.getLikes();
				if(users.contains(user)) {
					return removeLike(idUser, idComment);
				}
				comment.getLikes().add(user);
				comment.setLikeQuantity(1);
				commentRepository.save(comment);
				LikeUser like = new LikeUser(comment.getId(), TypeObject.COMMENT);
				user.getLikes().add(like);
				userService.save(user);
				CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
				return ResponseEntity.accepted().body(commentMiniDTO);
			}catch (RuntimeException e) {
				return ResponseEntity.badRequest().build();
			}
		}
		
		public ResponseEntity<Object> removeLike(String idUser, String idComment){
			try {
				User user = (User) userService.findById(idUser).getBody();
				Comment comment = commentRepository.findById(idComment).get();
				comment.getLikes().remove(user);
				comment.setLikeQuantity(-1);
				commentRepository.save(comment);
				LikeUser like = new LikeUser(comment.getId(), TypeObject.COMMENT);
				user.getLikes().remove(like);
				userService.save(user);
				CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
				return ResponseEntity.accepted().body(commentMiniDTO);
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
