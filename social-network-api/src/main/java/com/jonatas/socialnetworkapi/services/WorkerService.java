package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.repositories.WorkerRepository;

@Service
public class WorkerService {

	//repositories
	
	@Autowired
	private WorkerRepository workerRepository;
	
	//services
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityService entityService;
	
	//methods
	
	public ResponseEntity<Object> findAll(){
		List<Worker> list = workerRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			Worker worker = workerRepository.findById(id).get();
			return ResponseEntity.ok().body(worker);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> create(WorkerDTO workerDTO) {
		try {
			User user = (User) userService.findById(workerDTO.getUser()).getBody();
			Entity entity = (Entity) entityService.findById(workerDTO.getEntity()).getBody();
			Worker worker = new Worker(null, workerDTO.getRole(), user, entity);
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			workerRepository.save(worker);
			user.getWorkers().add(worker);
			userService.save(user);
			entity.getWorkers().add(worker);
			entityService.save(entity);
			return ResponseEntity.created(null).body(worker);
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	public ResponseEntity<Object> delete(String idWorker, String idUser){
		try {
			Worker worker = workerRepository.findById(idWorker).get();
			User user = worker.getUser();
			if(idUser.hashCode() != user.getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			user.getWorkers().remove(worker);
			Entity entity = worker.getEntity();
			entity.getWorkers().remove(worker);
			workerRepository.delete(worker);
			userService.save(user);
			entityService.save(entity);
			return ResponseEntity.ok().build();
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
