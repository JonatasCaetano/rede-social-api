package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EvaluationUserDTO;
import com.jonatas.socialnetworkapi.dto.UserAuthDTO;
import com.jonatas.socialnetworkapi.dto.UserCreationDTO;
import com.jonatas.socialnetworkapi.dto.UserUpdateDTO;
import com.jonatas.socialnetworkapi.dto.WorkerUserDTO;
import com.jonatas.socialnetworkapi.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class UserService {
	
	//repositories

	@Autowired
	private UserRepository userRepository;
	
	//services
	
	@Autowired
	@Lazy
	private FollowerService followerService;
	
	@Autowired
	@Lazy
	private InvitationService invitationService;
		
	//methods
	
	public ResponseEntity<Object> findAll() {
		List<User> users = userRepository.findAll();
		List<UserMiniDTO> userMiniDTOs = new ArrayList<>();
		for(User user : users) {
			UserMiniDTO userMiniDTO = new UserMiniDTO(user);
			userMiniDTOs.add(userMiniDTO);
		}
		return ResponseEntity.ok().body(userMiniDTOs);
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			User user = userRepository.findById(id).get();
			return ResponseEntity.ok().body(user);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> login(UserAuthDTO userAuthDTO){
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
	
	public ResponseEntity<Object> createUser(UserCreationDTO userCreation){
		try {
			try {
				String[] name = userCreation.getName().split(" ");
				String name1 = name[0];
				String name2 = name[1];
				if(name1.length() < 3 || name2.length() < 4) {
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
					User user = (User) invitationService.returnUser(userCreation.getInvitation()).getBody();			
					Follower follower = (Follower) followerService.insert(new Follower(null, obj)).getBody();
					obj.setFollower(follower);
					userRepository.save(obj);
					if(user.getId() != null) {
						followerService.addFollowing(obj.getId(), user.getId());
						followerService.addFollowing(user.getId(), obj.getId());
						}
					obj = (User) invitationService.createdInvitation(obj).getBody();
					userRepository.save(obj);
					return ResponseEntity.created(null).body(obj);
					
				}
			}
		}catch(RuntimeException e) {
			System.out.println("erro no try");
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> getWorkers(String id){
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
	
	public ResponseEntity<Object> save(User user){
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
	
	public ResponseEntity<Object> getEvaluationsUser(String id){
		try {
			User user = userRepository.findById(id).get();
			List<Evaluation> evaluations = new ArrayList<>();
			List<EntitySave> entitySaves = user.getEntitySaves();
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.isRated()) {
					evaluations.add(entitySave.getEvaluation());
				}
			}
			List<EvaluationUserDTO> evaluationUserDTOs = new ArrayList<>();
			for(Evaluation evaluation : evaluations) {
				EvaluationUserDTO evaluationUserDTO = new EvaluationUserDTO(evaluation);
				evaluationUserDTOs.add(evaluationUserDTO);
			}
			return ResponseEntity.ok().body(evaluationUserDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//put
	
	public ResponseEntity<Void> updateName(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setName(userUpdateDTO.getName());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateEmail(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setEmail(userUpdateDTO.getEmail());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updatePassword(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setPassword(userUpdateDTO.getPassword());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateImage(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setImage(userUpdateDTO.getImage());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateDescription(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setDescription(userUpdateDTO.getDescription());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateBirthDate(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setBirthDate(userUpdateDTO.getBirthDate());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateCity(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setCity(userUpdateDTO.getCity());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updatePrivacy(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setChecked(userUpdateDTO.isPrivacy());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateStatus(UserUpdateDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getId()).get();
			user.setStatus(userUpdateDTO.isStatus());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
