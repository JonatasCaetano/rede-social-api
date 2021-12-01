package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.AuthDTO;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowerRepository followerRepository;
	
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	public ResponseEntity<Object> auth(AuthDTO auth){
		try {
			User user = userRepository.findByEmail(auth.getEmail());
            if(auth.getPassword().hashCode() == user.getPassword().hashCode()) {
            	return ResponseEntity.ok().body(user.getId());
            }else {
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}	
	
	public ResponseEntity<User> saveUser(User user){
		try {
			User obj = userRepository.insert(user);
			Follower follower = followerRepository.insert(new Follower(null, obj));
			obj.setFollower(follower);
			userRepository.save(obj);
			return ResponseEntity.created(null).body(obj);
		}catch(RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<List<Worker>> getWorkers(String id){
		try {
			User user = userRepository.findById(id).get();
			List<Worker> list = user.getWorkers();
			return ResponseEntity.ok().body(list);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
