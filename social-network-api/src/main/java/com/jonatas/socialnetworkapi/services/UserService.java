package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.CreationUser;
import com.jonatas.socialnetworkapi.dto.UserAuthDTO;
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
	
	public ResponseEntity<Object> auth(UserAuthDTO userAuthDTO){
		try {
			User user = userRepository.findByEmail(userAuthDTO.getEmail());
            if(userAuthDTO.getPassword().hashCode() == user.getPassword().hashCode()) {
            	return ResponseEntity.ok().body(user.getId());
            }else {
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}	
	
	public ResponseEntity<CreationUser> saveUser(User user, String invitation){
	
		try {
			User obj = userRepository.insert(user);
			invitationService.addInvited(obj.getId(), invitation);
			Follower follower = followerRepository.insert(new Follower(null, obj));
			obj.setFollower(follower);
			userRepository.save(obj);
			invitationService.createdInvitation(obj.getId());
			CreationUser creationUser = new CreationUser(obj);
			return ResponseEntity.created(null).body(creationUser);
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
