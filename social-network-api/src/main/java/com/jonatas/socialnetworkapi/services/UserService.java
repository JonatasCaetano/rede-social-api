package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.UserCreation;
import com.jonatas.socialnetworkapi.dto.UserAuthDTO;
import com.jonatas.socialnetworkapi.dto.WorkerUserDTO;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class UserService {
	
	//repositories

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowerRepository followerRepository;
	
	//services
	
	@Autowired
	private InvitationService invitationService;
		
	//methods
	
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	public ResponseEntity<User> findById(String id){
		try {
			User user = userRepository.findById(id).get();
			return ResponseEntity.ok().body(user);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
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
	
	public ResponseEntity<Object> createUser(UserCreation userCreation){
		try {
			try {
				String[] name = userCreation.getName().split(" ");
				String name1 = name[0];
				String name2 = name[1];
				if(name1.length() <= 3 || name2.length() <= 4) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}catch (RuntimeException e) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			if(testEmail(userCreation.getEmail())) {
				return ResponseEntity.badRequest().body(userCreation.getEmail());
			}else {
				if(!invitationService.checkAvailability(userCreation.getInvitation()).getBody()) {
					return ResponseEntity.badRequest().body(userCreation.getInvitation());
				}else {
					User obj = userRepository.insert(new User(userCreation));
					invitationService.addInvited(obj, userCreation.getInvitation());
					//change followerRepository to followerService
					Follower follower = followerRepository.insert(new Follower(null, obj));
					obj.setFollower(follower);
					userRepository.save(obj);
					System.out.println("---");
					obj = invitationService.createdInvitation(obj).getBody();
					userRepository.save(obj);
					UserCreation creationUser = new UserCreation(obj);
					return ResponseEntity.created(null).body(creationUser);
					
				}
			}
		}catch(RuntimeException e) {
			System.out.println("erro no try");
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
	
	public ResponseEntity<User> save(User user){
		try {
			User obj = userRepository.save(user);
			return ResponseEntity.accepted().body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public boolean testEmail(String email) {
		try {
			User user = userRepository.findByEmail(email);
			user.getId();
			user.getName();
			return true;
		}catch (RuntimeException e) {
			return false;
		}
	}
}
