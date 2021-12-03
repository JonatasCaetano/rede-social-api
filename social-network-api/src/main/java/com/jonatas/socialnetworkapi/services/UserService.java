package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.AuthDTO;
import com.jonatas.socialnetworkapi.dto.UserDTO;
import com.jonatas.socialnetworkapi.dto.WorkerUserDTO;
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
	
	@Autowired
	private InvitationService invitationService;
	
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
	
	public ResponseEntity<UserDTO> saveUser(User user, String invitation){
	
		try {
			User obj = userRepository.insert(user);
			invitationService.addInvited(obj.getId(), invitation);
			Follower follower = followerRepository.insert(new Follower(null, obj));
			obj.setFollower(follower);
			userRepository.save(obj);
			invitationService.createdInvitation(obj.getId());
			UserDTO userDTO = new UserDTO(obj);
			return ResponseEntity.created(null).body(userDTO);
		}catch(RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<List<WorkerUserDTO>> getWorkers(String id){
		try {
			User user = userRepository.findById(id).get();
			List<Worker> workers = user.getWorkers();
			List<WorkerUserDTO> workerUserDTOs = new ArrayList<>();
			for(Worker worker : workers) {
				WorkerUserDTO workerUserDTO = new WorkerUserDTO(worker);
				workerUserDTOs.add(workerUserDTO);
			}
			return ResponseEntity.ok().body(workerUserDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
