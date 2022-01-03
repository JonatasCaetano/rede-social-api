package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Comment;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.PostDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.CommentMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.PostMiniDTO;
import com.jonatas.socialnetworkapi.repositories.PostRepository;

@Service
public class PostService {
	
	//repositories

	@Autowired
	private PostRepository postRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private EntityService entityService;
	
	@Autowired
	@Lazy
	private SeasonService seasonService;
	
	@Autowired
	@Lazy
	private EpisodeService episodeService;
	
	//methods
	
	//get
	
	public ResponseEntity<Object> findAllMini(){
		try {
			List<Post> posts = postRepository.findAll();
			List<PostMiniDTO> postMiniDTOs = new ArrayList<>();
			for(Post post : posts) {
				PostMiniDTO postMiniDTO = new PostMiniDTO(post);
				postMiniDTOs.add(postMiniDTO);
			}
			return ResponseEntity.ok().body(postMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Post post = postRepository.findById(id).get();
			PostMiniDTO postMiniDTO = new PostMiniDTO(post);
			return ResponseEntity.ok().body(postMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getCommentsMini(String id){
		try {
			Post post = postRepository.findById(id).get();
			List<Comment> comments = post.getComments();
			List<CommentMiniDTO> commentMiniDTOs = new ArrayList<>();
			for(Comment comment : comments) {
				CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
				commentMiniDTOs.add(commentMiniDTO);
			}
			return ResponseEntity.ok().body(commentMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> getLikes(String id){
		try {
			Post post = postRepository.findById(id).get();
			List<User> likes = post.getLikes();
			return ResponseEntity.ok().body(likes);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
	public ResponseEntity<Object> newPost(PostDTO postDTO){
		try {
			User user = (User) userService.findById(postDTO.getIdUser()).getBody();
			Entity entity = (Entity) entityService.findById(postDTO.getIdEntity()).getBody();
			Season season = (Season) seasonService.findById(postDTO.getIdSeason()).getBody();
			Episode episode = (Episode) episodeService.findById(postDTO.getIsEpisode()).getBody();
			Post post = new Post(postDTO.getTypePost(), postDTO.getRelease(), postDTO.getBody(), postDTO.getCategory(), user, entity, season, episode);
			post = postRepository.insert(post);
			user.getPosts().add(post);
			userService.save(user);
			switch (post.getTypePost()) {
			case ENTITY:
				entity.getPosts().add(post);
				entityService.save(entity);
				break;
			case SEASON:
				season.getPosts().add(post);
				seasonService.save(season);
				break;
			case EPISODE:
				episode.getPosts().add(post);
				episodeService.save(episode);
				break;	
			}
			PostMiniDTO postMiniDTO = new PostMiniDTO(post);
			return ResponseEntity.created(null).body(postMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> addLike(String idUser, String idPost){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			List<User> users = post.getLikes();
			if(users.contains(user)) {
				return removeLike(idUser, idPost);
			}
			post.getLikes().add(user);
			postRepository.save(post);
			user.getLikes().add(post);
			userService.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	//delete
	
	public ResponseEntity<Object> deletePost(PostDTO postDTO){
		try {
			User user = (User) userService.findById(postDTO.getIdUser()).getBody();
			Entity entity = (Entity) entityService.findById(postDTO.getIdEntity()).getBody();
			Season season = (Season) seasonService.findById(postDTO.getIdSeason()).getBody();
			Episode episode = (Episode) episodeService.findById(postDTO.getIsEpisode()).getBody();
			Post post = postRepository.findById(postDTO.getIdPost()).get();
			if(user.getId().hashCode() != post.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			user.getPosts().remove(post);
			List<Post> likesUser = user.getLikes();
			likesUser.remove(post);
			userService.save(user);
			
			switch (post.getTypePost()) {
			case ENTITY:
				entity.getPosts().remove(post);
				entityService.save(entity);
				break;
			case SEASON:
				season.getPosts().remove(post);
				seasonService.save(season);
				break;
			case EPISODE:
				episode.getPosts().remove(post);
				episodeService.save(episode);
				break;	
			}
			postRepository.delete(post);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
			
	public ResponseEntity<Object> removeLike(String idUser, String idPost){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			post.getLikes().remove(user);
			postRepository.save(post);
			user.getLikes().remove(post);
			userService.save(user);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	//internal
	
	public ResponseEntity<Object> findById(String id){
		try {
			Post post = postRepository.findById(id).get();
			return ResponseEntity.ok().body(post);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> save(Post post){
		try {
			postRepository.save(post);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
