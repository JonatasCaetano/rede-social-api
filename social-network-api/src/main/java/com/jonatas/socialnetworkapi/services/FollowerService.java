package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.UserMiniDTO;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;

@Service
public class FollowerService {

	//repositories
	
	@Autowired
	private FollowerRepository followerRepository;
	
	//services
	
	@Autowired
	private UserService userService;
	
	//methods
	
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
			User user = userService.findById(id).getBody();
			Follower follower = followerRepository.findByUser(user);
			return ResponseEntity.ok().body(follower);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> addFollowing(String followerId, String followingId){
		try {
			User userFollower = userService.findById(followerId).getBody();
			User userFollowing = userService.findById(followingId).getBody();
			Follower follower = followerRepository.findByUser(userFollower);
			if(follower.getFollowing().contains(userFollowing)) {
				return ResponseEntity.ok().build();
			}else {
				follower.getFollowing().add(userFollowing);
				followerRepository.save(follower);
				userFollower.setFollowing(userFollower.getFollowing() + 1);
				userService.save(userFollower);
				userFollowing.setFollowers(userFollowing.getFollowers() + 1);
				userService.save(userFollowing);
				return ResponseEntity.accepted().build();
			}
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	public ResponseEntity<Void> removeFollowing(String followerId, String followingId){
		try {
			User userFollower = userService.findById(followerId).getBody();
			User userFollowing = userService.findById(followingId).getBody();
			Follower follower = followerRepository.findByUser(userFollower);
			if(!follower.getFollowing().contains(userFollowing)) {
				return ResponseEntity.ok().build();
			}else {
				follower.getFollowing().remove(userFollowing);
				followerRepository.save(follower);
				userFollower.setFollowing(userFollower.getFollowing() - 1);
				userService.save(userFollower);
				userFollowing.setFollowers(userFollowing.getFollowers() - 1);
				userService.save(userFollowing);
				return ResponseEntity.accepted().build();
			}
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	
	public ResponseEntity<List<UserMiniDTO>> getAllFollowing(String userId){
		try {
			User user = userService.findById(userId).getBody();
			Follower follower = followerRepository.findByUser(user);
			List<User> users = follower.getFollowing();
			List<UserMiniDTO> userMiniDTOs = new ArrayList<>();
			for(User userList : users) {
				UserMiniDTO userMiniDTO = new UserMiniDTO(userList);
				userMiniDTOs.add(userMiniDTO);
			}
			return ResponseEntity.ok().body(userMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		
		}
		
		
	}
	
	public ResponseEntity<List<UserMiniDTO>> getAllFollower(String userId){
		try {
			User user = userService.findById(userId).getBody();
			List<Follower> followers = followerRepository.findAll();
			List<UserMiniDTO> userMiniDTOs = new ArrayList<>();
			for(Follower follower : followers) {
				if(follower.getFollowing().contains(user)) {
					UserMiniDTO userMiniDTO = new UserMiniDTO(follower.getUser());
					userMiniDTOs.add(userMiniDTO);
				}
			}
			return ResponseEntity.ok().body(userMiniDTOs);
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Follower> insert(Follower follower){
		try {
			Follower obj = followerRepository.insert(follower);
			return ResponseEntity.ok().body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	
}
