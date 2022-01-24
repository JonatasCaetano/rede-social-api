package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Comment;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.entities.dto.UserDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.CommentMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.InvitationMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.PostMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.WorkerMiniDTO;
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
	
	//get
	
	public ResponseEntity<Object> findAllMini() {
		try {
			List<User> users = userRepository.findAll();
			List<UserMiniDTO> userMiniDTOs = new ArrayList<>();
			for(User user : users) {
				UserMiniDTO userMiniDTO = new UserMiniDTO(user);
				userMiniDTOs.add(userMiniDTO);
			}
			return ResponseEntity.ok().body(userMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByName(String name){
		try {
			List<User> users = userRepository.searchByName(name);
			List<UserMiniDTO> userMiniDTOs = new ArrayList<>();
			for(User user : users) {
				UserMiniDTO userMiniDTO = new UserMiniDTO(user);
				userMiniDTOs.add(userMiniDTO);
			}
			return ResponseEntity.ok().body(userMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			User user = userRepository.findById(id).get();
			UserMiniDTO userMiniDTO = new UserMiniDTO(user);
			return ResponseEntity.ok().body(userMiniDTO);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getWorkersMini(String id){
		try {
			User user = userRepository.findById(id).get();
			List<Worker> workers = user.getWorkers();
			List<WorkerMiniDTO> workerMiniDTOs = new ArrayList<>();
			for(Worker worker : workers) {
				WorkerMiniDTO workerMiniDTO = new WorkerMiniDTO(worker);
				workerMiniDTOs.add(workerMiniDTO);
			}
			return ResponseEntity.ok().body(workerMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getPostsMini(String id){
		try {
			User user = userRepository.findById(id).get();
			List<Post> posts = user.getPosts();
			List<PostMiniDTO> postMiniDTOs = new ArrayList<>();
			for(Post post : posts) {
				PostMiniDTO postMiniDTO = new PostMiniDTO(post);
				postMiniDTOs.add(postMiniDTO);
			}
			return ResponseEntity.ok().body(postMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getCommentsMini(String id){
		try {
			User user = userRepository.findById(id).get();
			List<Comment> comments = user.getComments();
			List<CommentMiniDTO> commentMiniDTOs = new ArrayList<>();
			for(Comment comment : comments) {
				CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
				commentMiniDTOs.add(commentMiniDTO);
			}
			return ResponseEntity.ok().body(commentMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getLikesMini(String id){
		try {
			User user = userRepository.findById(id).get();
			List<Post> likes = user.getLikes();
			List<PostMiniDTO> postMiniDTOs = new ArrayList<>();
			for(Post post : likes) {
				PostMiniDTO postMiniDTO = new PostMiniDTO(post);
				postMiniDTOs.add(postMiniDTO);
			}
			return ResponseEntity.ok().body(postMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getInvitationMini(String id){
		try {
			User user = userRepository.findById(id).get();
			Invitation invitation = user.getInvitation();
			InvitationMiniDTO invitationMiniDTO = new InvitationMiniDTO(invitation);
			return ResponseEntity.ok().body(invitationMiniDTO);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
		
	public ResponseEntity<Object> loginMini(String email, String password){
		try {
			User user = userRepository.findByEmail(email);
            if(password.hashCode() == user.getPassword().hashCode()) {
            	return ResponseEntity.ok().body(user.getId());
            }else {
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> checkEmail(String email){
		try {
			User user = userRepository.findByEmail(email);
			if(user.getEmail() != null) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();	
			}
			return ResponseEntity.badRequest().build();
		}catch (RuntimeException e) {
			return ResponseEntity.accepted().build();
		}
	}
	
	public ResponseEntity<Object> checkName(String name){
		try {
			String[] nameVector = name.split(" ");
			String name1 = nameVector[0];
			String name2 = nameVector[1];
			if(name1.length() < 3 || name2.length() < 4) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();	
			}
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
			
	//post
	
	public ResponseEntity<Object> createUser(UserDTO userDTO){
		try {
			checkEmail(userDTO.getEmail());
			User obj = userRepository.insert(new User(userDTO));
			invitationService.addInvited(obj, userDTO.getInvitation());		
			Follower follower = (Follower) followerService.insert(new Follower(null, obj)).getBody();
			obj.setFollower(follower);
			userRepository.save(obj);	
			try {
				invitationService.createdInvitation(obj);
			}catch (RuntimeException e) {
				return ResponseEntity.badRequest().build();
			}
			try {
				User user = (User) invitationService.returnUser(userDTO.getInvitation()).getBody();
				followerService.addFollowing(obj.getId(), user.getId());
				followerService.addFollowing(user.getId(), obj.getId());
			}catch (RuntimeException e) {
				return ResponseEntity.badRequest().build();
			}
			obj = userRepository.findById(obj.getId()).get();
			return ResponseEntity.created(null).body(obj.getId());
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//put
	
	public ResponseEntity<Void> updateName(UserDTO userUpdateDTO){
		try {
			String[] name = userUpdateDTO.getName().split(" ");
			String name1 = name[0];
			String name2 = name[1];
			if(name1.length() < 3 || name2.length() < 4) {
				return ResponseEntity.badRequest().build();
			}
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setName(userUpdateDTO.getName());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updateEmail(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findByEmail(userUpdateDTO.getEmail());
			if(user.getEmail() != null) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();	
			}
		}catch (Exception e) {
			try {
				User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
				user.setEmail(userUpdateDTO.getEmail());
				userRepository.save(user);
				return ResponseEntity.accepted().build();
			}catch (RuntimeException e) {
				return ResponseEntity.badRequest().build();
			}
		}
		
	}
	
	public ResponseEntity<Void> updatePassword(UserDTO userUpdateDTO){
		try {
			if(userUpdateDTO.getPassword().length() < 6) {
				return ResponseEntity.badRequest().build();
			}
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setPassword(userUpdateDTO.getPassword());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> addImage(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setImage(userUpdateDTO.getImage());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> removeImage(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setImage(null);
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updateDescription(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setDescription(userUpdateDTO.getDescription());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updatePlace(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setPlace(userUpdateDTO.getPlace());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updatePrivacy(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setPrivacy(userUpdateDTO.isPrivacy());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updateStatus(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setStatus(userUpdateDTO.isStatus());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updateChecked(UserDTO userUpdateDTO){
		try {
			User user = userRepository.findById(userUpdateDTO.getIdUser()).get();
			user.setChecked(userUpdateDTO.isChecked());
			userRepository.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	//internal
	
	public ResponseEntity<Object> findById(String id){
		try {
			User user = userRepository.findById(id).get();
			return ResponseEntity.ok().body(user);
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
	
}
