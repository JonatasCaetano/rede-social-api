package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.dto.mini.WorkerMiniDTO;
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
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private EntityService entityService;
	
	//methods
	
	//get
	
	public ResponseEntity<Object> findAllMini(){
		try {
			List<Worker> list = workerRepository.findAll();
			List<WorkerMiniDTO> workerMiniDTOs = new ArrayList<>();
			for(Worker worker : list) {
				WorkerMiniDTO workerMiniDTO = new WorkerMiniDTO(worker);
				workerMiniDTOs.add(workerMiniDTO);
			}
			return ResponseEntity.ok(workerMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Worker worker = workerRepository.findById(id).get();
			WorkerMiniDTO workerMiniDTO = new WorkerMiniDTO(worker);
			return ResponseEntity.ok().body(workerMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
	public ResponseEntity<Object> newWorker(WorkerDTO workerDTO) {
		try {
			User user = (User) userService.findById(workerDTO.getIdUser()).getBody();
			Entity entity = (Entity) entityService.findById(workerDTO.getIdEntity()).getBody();
			Worker worker = new Worker(null, workerDTO.getRole(), user, entity, 1);
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			workerRepository.save(worker);
			user.getWorkers().add(worker);
			userService.save(user);
			entity.getWorkers().add(worker);
			entityService.save(entity);
			WorkerMiniDTO workerMiniDTO = new WorkerMiniDTO(worker);
			return ResponseEntity.created(null).body(workerMiniDTO);
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	//delete
	
	public ResponseEntity<Object> deleteWorker(String idWorker, String idUser){
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
