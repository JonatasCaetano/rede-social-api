package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	
	public ResponseEntity<Worker> saveNewWorker(Worker worker) {
		try {
			workerRepository.save(worker);
			User user = userRepository.findById(worker.getUser().getId()).get();
			Entity entity = entityRepository.findById(worker.getEntity().getId()).get();
			user.getWorkers().add(worker);
			userRepository.save(user);
			entity.getWorkers().add(worker);
			entityRepository.save(entity);
			return ResponseEntity.created(null).body(worker);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
