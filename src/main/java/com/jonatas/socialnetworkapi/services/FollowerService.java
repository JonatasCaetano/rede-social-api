package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.mini.FollowerMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.UserMicroDTO;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;

@Service
public class FollowerService {

	//repositories
	
	@Autowired
	private FollowerRepository followerRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	//methods
	
	public ResponseEntity<Object> findAllMini(){
		try {		
			List<Follower> followers = followerRepository.findAll();
			List<FollowerMiniDTO> followerMiniDTOs = new ArrayList<>();
			for(Follower follower : followers) {
				FollowerMiniDTO followerMiniDTO = new FollowerMiniDTO(follower);
				followerMiniDTOs.add(followerMiniDTO);
			}
			return ResponseEntity.ok().body(followerMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id) {
		try {		
			Follower follower = followerRepository.findById(id).get();
			FollowerMiniDTO followerMiniDTO = new FollowerMiniDTO(follower);
			return ResponseEntity.ok().body(followerMiniDTO);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<Object> getAllFollowingMini(String userId, String sendId){
		try {
			User Userfollower = (User) userService.findById(userId).getBody();
			User send = (User) userService.findById(sendId).getBody();
			//Follower follower = Userfollower.getFollower();
			List<User> users = Userfollower.getFollower().getFollowing();
			List<UserMicroDTO> userMicroDtos = new ArrayList<>();
			for(User user : users) {
				UserMicroDTO userMicroDto = new UserMicroDTO(user, send);
				userMicroDtos.add(userMicroDto);
			}
			return ResponseEntity.ok().body(userMicroDtos);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> getAllFollowerMini(String userId, String sendId){
		try {
			User userFollowing = (User) userService.findById(userId).getBody();
			User send = (User) userService.findById(sendId).getBody();
			List<Follower> followers = followerRepository.findAll();
			List<UserMicroDTO> userMicroDtos = new ArrayList<>();
			for(Follower follower : followers) {
				if(follower.getFollowing().contains(userFollowing)) {
					UserMicroDTO userMicroDto = new UserMicroDTO(follower.getUser(), send);
					userMicroDtos.add(userMicroDto);
				}
			}
			return ResponseEntity.ok().body(userMicroDtos);
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
	   }
	}
	
	public ResponseEntity<Object> CheckFollowing(String userId, String followingId){
		try {
			User user = (User) userService.findById(userId).getBody();
			User following = (User) userService.findById(followingId).getBody();
			List<User> followers = user.getFollower().getFollowing();
			if(followers.contains(following)) {
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
	   }
	}
	
	//post
	
	public ResponseEntity<Void> addFollowing(String followerId, String followingId){
		try {
			User userFollower = (User) userService.findById(followerId).getBody();
			User userFollowing = (User) userService.findById(followingId).getBody();
			Follower follower = userFollower.getFollower();
			if(userFollower.equals(userFollowing)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			if(follower.getFollowing().contains(userFollowing)) {
				return ResponseEntity.ok().build();
			}else {
				follower.getFollowing().add(userFollowing);
				followerRepository.save(follower);
//				userFollower.setQuantityFollowing(+ 1);
//				userService.save(userFollower);
				userFollowing.setQuantityFollowers(+ 1);
				userService.save(userFollowing);
				return ResponseEntity.accepted().build();
			}
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//delete
	
	public ResponseEntity<Void> removeFollowing(String followerId, String followingId){
		try {
			User userFollower = (User) userService.findById(followerId).getBody();
			User userFollowing = (User) userService.findById(followingId).getBody();
			Follower follower = userFollower.getFollower();
			if(!follower.getFollowing().contains(userFollowing)) {
				return ResponseEntity.ok().build();
			}else {
				follower.getFollowing().remove(userFollowing);
				followerRepository.save(follower);
//				userFollower.setQuantityFollowing(- 1);
//				userService.save(userFollower);
				userFollowing.setQuantityFollowers(- 1);
				userService.save(userFollowing);
				return ResponseEntity.accepted().build();
			}
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
			
	//internal
	
	public ResponseEntity<Object> insert(Follower follower){
		try {
			Follower obj = followerRepository.insert(follower);
			return ResponseEntity.ok().body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> findById(String id) {
		try {		
			Follower follower = followerRepository.findById(id).get();
			return ResponseEntity.ok().body(follower);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
}
