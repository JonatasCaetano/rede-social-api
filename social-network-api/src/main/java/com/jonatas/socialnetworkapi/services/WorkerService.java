package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;
import com.jonatas.socialnetworkapi.repositories.WorkerRepository;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository workerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityRepository entityRepository;
	
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> list = workerRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	public ResponseEntity<Worker> create(WorkerDTO workerDTO) {
		try {
			User user = userRepository.findById(workerDTO.getUser()).get();
			Entity entity = entityRepository.findById(workerDTO.getEntity()).get();
			Worker worker = new Worker(null, user, entity, workerDTO.getRole());
			workerRepository.save(worker);
			user.getWorkers().add(worker);
			userRepository.save(user);
			entity.getWorkers().add(worker);
			entityRepository.save(entity);
			return ResponseEntity.created(null).body(worker);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	public ResponseEntity<Void> delete(String id){
		try {
			Worker worker = workerRepository.findById(id).get();
			User user = worker.getUser();
			user.getWorkers().remove(worker);
			Entity entity = worker.getEntity();
			entity.getWorkers().remove(worker);
			workerRepository.delete(worker);
			userRepository.save(user);
			entityRepository.save(entity);
			return ResponseEntity.ok().build();
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
