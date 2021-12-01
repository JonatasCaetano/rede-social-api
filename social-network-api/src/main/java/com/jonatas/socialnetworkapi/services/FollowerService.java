package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	
	public ResponseEntity<Follower> findById(String id) {
		try {		
			Follower follower = followerRepository.findById(id).get();
			return ResponseEntity.ok().body(follower);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> addFollowing(String userId, String following){
		try {
			Follower follower = followerRepository.findByUser(userId);
			User user = userRepository.findById(following).get();
			follower.getFollowing().add(user);
			followerRepository.save(follower);
			return ResponseEntity.accepted().build();
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
