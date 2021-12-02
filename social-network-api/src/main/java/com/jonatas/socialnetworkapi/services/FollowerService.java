package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.AuthDTO;
import com.jonatas.socialnetworkapi.dto.AuthorDTO;
import com.jonatas.socialnetworkapi.dto.UserDTO;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class FollowerService {

	@Autowired
	private FollowerRepository followerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<List<Follower>> findAll(){
		try {		
			List<Follower> followers = followerRepository.findAll();
			return ResponseEntity.ok().body(followers);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Follower> findById(String id) {
		try {		
			Follower follower = followerRepository.findById(id).get();
			return ResponseEntity.ok().body(follower);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Follower> findByUser(String id) {
		try {		
			User user = userRepository.findById(id).get();
			Follower follower = followerRepository.findByUser(user);
			return ResponseEntity.ok().body(follower);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> addFollowing(String followerId, String followingId){
		try {
			User userFollower = userRepository.findById(followerId).get();
			User userFollowing = userRepository.findById(followingId).get();
			Follower follower = followerRepository.findByUser(userFollower);
			if(follower.getFollowing().contains(userFollowing)) {
				return ResponseEntity.ok().build();
			}else {
				follower.getFollowing().add(userFollowing);
				followerRepository.save(follower);
				return ResponseEntity.accepted().build();
			}
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	public ResponseEntity<Void> removeFollowing(String followerId, String followingId){
		try {
			User userFollower = userRepository.findById(followerId).get();
			User userFollowing = userRepository.findById(followingId).get();
			Follower follower = followerRepository.findByUser(userFollower);
			if(!follower.getFollowing().contains(userFollowing)) {
				return ResponseEntity.ok().build();
			}else {
				follower.getFollowing().remove(userFollowing);
				followerRepository.save(follower);
				return ResponseEntity.accepted().build();
			}
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	
	public ResponseEntity<List<AuthorDTO>> getAllFollowing(String userId){
		try {
			User user = userRepository.findById(userId).get();
			Follower follower = followerRepository.findByUser(user);
			List<User> users = follower.getFollowing();
			List<AuthorDTO> authorDTOs = new ArrayList<>();
			for(User userList : users) {
				AuthorDTO authorDTO = new AuthorDTO(userList);
				authorDTOs.add(authorDTO);
			}
			return ResponseEntity.ok().body(authorDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		
		}
		
		
	}
	
	
	
	
}
