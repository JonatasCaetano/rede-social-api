package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.PostDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
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
	
	public ResponseEntity<Object> findAll(){
		try {
			List<Post> posts = postRepository.findAll();
			return ResponseEntity.ok().body(posts);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			Post post = postRepository.findById(id).get();
			return ResponseEntity.ok().body(post);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newPost(PostDTO postDTO){
		try {
			User user = (User) userService.findById(postDTO.getUser()).getBody();
			Entity entity = (Entity) entityService.findById(postDTO.getEntity()).getBody();
			Season season = (Season) seasonService.findById(postDTO.getSeason()).getBody();
			Episode episode = (Episode) episodeService.findById(postDTO.getEpisode()).getBody();
			Post post = new Post(postDTO.getType(), postDTO.getRelease(), postDTO.getBody(), postDTO.getCategory(), user, entity, season, episode);
			post = postRepository.insert(post);
			user.getPosts().add(post);
			userService.save(user);
			switch (post.getCategory()) {
			case 0:
				entity.getPosts().add(post);
				entityService.save(entity);
				break;
			case 1:
				season.getPosts().add(post);
				seasonService.save(season);
				break;
			case 2:
				episode.getPosts().add(post);
				episodeService.save(episode);
				break;	
			}
			return ResponseEntity.created(null).body(post);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> deletePost(PostDTO postDTO){
		try {
			User user = (User) userService.findById(postDTO.getUser()).getBody();
			Entity entity = (Entity) entityService.findById(postDTO.getEntity()).getBody();
			Season season = (Season) seasonService.findById(postDTO.getSeason()).getBody();
			Episode episode = (Episode) episodeService.findById(postDTO.getEpisode()).getBody();
			Post post = postRepository.findById(postDTO.getId()).get();
			user.getPosts().remove(post);
			userService.save(user);
			switch (post.getCategory()) {
			case 0:
				entity.getPosts().remove(post);
				entityService.save(entity);
				break;
			case 1:
				season.getPosts().remove(post);
				seasonService.save(season);
				break;
			case 2:
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
}
