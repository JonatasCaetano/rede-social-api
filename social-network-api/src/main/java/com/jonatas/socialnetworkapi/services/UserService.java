package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Auth;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	public ResponseEntity<Object> auth(Auth auth){
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
}
