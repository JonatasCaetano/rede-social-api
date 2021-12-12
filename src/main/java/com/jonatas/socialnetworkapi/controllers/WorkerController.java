package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.services.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {
	
	//services

	@Autowired
	private WorkerService workerService;
	
	//get
	
	@GetMapping(value = "get/workers")
	public ResponseEntity<Object> findAllMini(){
		return workerService.findAllMini();
	}
	
	@GetMapping(value = "get/worker/{id}")
	public ResponseEntity<Object> findByIdMini(@PathVariable String id){
		return workerService.findByIdMini(id);
	}
	
	//post
	
	@PostMapping(value = "post/worker")
	public ResponseEntity<Object> newWorker(@RequestBody WorkerDTO workerDTO){
		return workerService.newWorker(workerDTO);
	}
	
	//delete
	
	@DeleteMapping(value = "delete/worker/{idWorker}/user/{idUser}")
	public ResponseEntity<Object> delete(@PathVariable String idWorker, @PathVariable String idUser){
		return workerService.deleteWorker(idWorker, idUser);
	
	}
}
