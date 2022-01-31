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
import com.jonatas.socialnetworkapi.entities.dto.PostUpdateDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.CommentMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.PostUpdateMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.entities.helper.Like;
import com.jonatas.socialnetworkapi.entities.post.Update;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;
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
			List<PostUpdateMiniDTO> postUpdateMiniDTOs = new ArrayList<>();
			for(Post post : posts) {
				PostUpdateMiniDTO postUpdateMiniDTO = new PostUpdateMiniDTO((Update) post);
				postUpdateMiniDTOs.add(postUpdateMiniDTO);
			}
			return ResponseEntity.ok().body(postUpdateMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Update post = (Update) postRepository.findById(id).get();
			PostUpdateMiniDTO postUpdateMiniDTO = new PostUpdateMiniDTO(post);
			return ResponseEntity.ok().body(postUpdateMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getPostAll(String id){
		try {
			User user = (User) userService.findById(id).getBody();
			List<Post> objs = postRepository.findAll();
			List<Post> posts = new ArrayList<>();
			List<String> ids = new ArrayList<>();
			for(User following : user.getFollower().getFollowing()) {
				ids.add(following.getId());
			}
			int value = 0;
			for(Post post : objs) {
				if(post.getTypePostVisibility() == TypePostVisibility.USER) {
					if(ids.contains(post.getUser().getId()) || user.getId().hashCode() == post.getUser().getId().hashCode() ) {
						posts.add(post);
						value += value;
						if(value >= 500) {
							return ResponseEntity.ok().body(posts); 
						}
					}
				}
			}
			
			return ResponseEntity.ok().body(posts);
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
			List<UserMiniDTO> userMiniDTOs = new ArrayList<>();
			for(User user : likes) {
				UserMiniDTO userMiniDTO = new UserMiniDTO(user);
				userMiniDTOs.add(userMiniDTO);
			}
			return ResponseEntity.ok().body(userMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
	public ResponseEntity<Object> newPostUpdate(PostUpdateDTO postDTO){
		try {
			if(postDTO.getRelease() == null) {
				return ResponseEntity.badRequest().build();
			}
			User user = (User) userService.findById(postDTO.getIdUser()).getBody();
			Entity entity = (Entity) entityService.findById(postDTO.getIdEntity()).getBody();
			Season season = (Season) seasonService.findById(postDTO.getIdSeason()).getBody();
			Episode episode = (Episode) episodeService.findById(postDTO.getIdEpisode()).getBody();
			Update post = new Update(
					postDTO.getRelease(),
					postDTO.getBody(),
					postDTO.getTypePost(),
					postDTO.getTypePostVisibility(),
					user,
					postDTO.getSpoiler(),
					postDTO.getCategory(),
					postDTO.getLevel(),
					postDTO.getEvaluation(),
					entity,
					season,
					episode
					);
			Post obj = post;
			obj = postRepository.insert(obj);
			//user.getPosts().add(post);
			//userService.save(user);
			PostUpdateMiniDTO postUpdateMiniDTO = new PostUpdateMiniDTO(post);
			return ResponseEntity.created(null).body(postUpdateMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
		
	public ResponseEntity<Object> addBodyUpdatePost(PostUpdateDTO postUpdateDTO){
		try {
			User user = (User) userService.findById(postUpdateDTO.getIdUser()).getBody();
			Post post = (Update) postRepository.findById(postUpdateDTO.getIdPost()).get();
			
			if(!(user.getId().hashCode() == post.getUser().getId().hashCode())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			post.setBody(postUpdateDTO.getBody());
			post.setSpoiler(postUpdateDTO.getSpoiler());
			postRepository.save(post);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//delete
	
	public ResponseEntity<Object> deletePost(String idPost, String idUser){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Update post = (Update) postRepository.findById(idPost).get();
			if(user.getId().hashCode() != post.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			postRepository.delete(post);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	//put
	
	public ResponseEntity<Object> addLike(String idUser, String idPost){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			List<User> users = post.getLikes();
			if(users.contains(user)) {
				return removeLike(idUser, idPost);
			}
			post.getLikes().add(user);
			post.setLikeQuantity(1);
			postRepository.save(post);
			Like like = new Like(post.getId(), TypeObject.POST);
			user.getLikes().add(like);
			userService.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> removeLike(String idUser, String idPost){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			post.getLikes().remove(user);
			post.setLikeQuantity(-1);
			postRepository.save(post);
			Like like = new Like(post.getId(), TypeObject.POST);
			user.getLikes().remove(like);
			userService.save(user);
			return ResponseEntity.accepted().build();
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
