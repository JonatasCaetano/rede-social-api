package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.EvaluationDTO;
import com.jonatas.socialnetworkapi.services.EvaluationService;

@RestController
@RequestMapping(value = "/evaluations")
public class EvaluationController {

	//services
	
	@Autowired
	private EvaluationService evaluationService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll() {
		return evaluationService.findAll();
	}
	
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return evaluationService.findById(id);
	}
	
	//post
	
	@PostMapping(value = "post")
	private ResponseEntity<Object> newEvaluation(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.newEvaluation(evaluationDTO);
	}
	
	@PutMapping(value = "put/update")
	private ResponseEntity<Void> updateEvaluation(@RequestBody EvaluationDTO evaluationTO){
		return evaluationService.updateEvaluation(evaluationTO);
	}
	
	@DeleteMapping(value = "delete")
	public ResponseEntity<Void> deleteEvaluation(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.deleteEvaluation(evaluationDTO);
	}
}
