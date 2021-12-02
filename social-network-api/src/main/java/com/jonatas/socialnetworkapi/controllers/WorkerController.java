package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.services.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	
	@GetMapping(value = "get/all")
	public ResponseEntity<List<Worker>> findAll(){
		return workerService.findAll();
	}
	
	@PostMapping(value = "post/create")
	public ResponseEntity<Worker> create(@RequestBody WorkerDTO workerDTO){
		return workerService.create(workerDTO);
	}
}
