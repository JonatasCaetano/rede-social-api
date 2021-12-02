package com.jonatas.socialnetworkapi.services;

import java.util.List;

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
			boolean exists = false;
			User userFollower = userRepository.findById(followerId).get();
			User userFollowing = userRepository.findById(followingId).get();
			Follower follower = followerRepository.findByUser(userFollower);
			List<User> users = follower.getFollowing();
			for(User user : users) {
				System.out.println("users add: " + user.getId());
				if(user.getId().hashCode() == followingId.hashCode()) {
					exists = true;
				}
			}
			if(exists) {
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
			boolean exists = false;
			int n = 0;
			User userFollower = userRepository.findById(followerId).get();
			User userFollowing = userRepository.findById(followingId).get();
			Follower follower = followerRepository.findByUser(userFollower);
			List<User> users = follower.getFollowing();
			
			for(User user : users) {
				System.out.println("users remove: " + user.getId());
				if(user.getId().hashCode() == followingId.hashCode()) {
					exists = true;
					n = users.indexOf(user);
				}
			}
			if(!exists) {
				return ResponseEntity.ok().build();
			}else {
				follower.getFollowing().remove(n);
				followerRepository.save(follower);
				return ResponseEntity.accepted().build();
			}
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
