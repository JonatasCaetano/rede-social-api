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
	
	@GetMapping(value = "get/evalutions")
	public ResponseEntity<Object> findAllMini() {
		return evaluationService.findAllMini();
	}
	
	@GetMapping(value = "get/evaluation/{id}")
	public ResponseEntity<Object> findByIdMini(@PathVariable String id){
		return evaluationService.findByIdMini(id);
	}
	
	//post
	
	@PostMapping(value = "post/entity")
	private ResponseEntity<Object> newEvaluationEntity(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.newEvaluationEntity(evaluationDTO);
	}
	
	@PostMapping(value = "post/season")
	private ResponseEntity<Object> newEvaluationSeason(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.newEvaluationSeason(evaluationDTO);
	}
	
	@PostMapping(value = "post/episode")
	private ResponseEntity<Object> newEvaluationEpisode(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.newEvaluationEpisode(evaluationDTO);
	}
	
	//put
	
	@PutMapping(value = "put/entity")
	private ResponseEntity<Void> updateEvaluationEntity(@RequestBody EvaluationDTO evaluationTO){
		return evaluationService.updateEvaluationEntity(evaluationTO);
	}
	
	@PutMapping(value = "put/season")
	private ResponseEntity<Void> updateEvaluationSeason(@RequestBody EvaluationDTO evaluationTO){
		return evaluationService.updateEvaluationSeason(evaluationTO);
	}
	
	@PutMapping(value = "put/episode")
	private ResponseEntity<Void> updateEvaluationEpisode(@RequestBody EvaluationDTO evaluationTO){
		return evaluationService.updateEvaluationEpisode(evaluationTO);
	}
	
	//delete
	
	@DeleteMapping(value = "delete/entity")
	public ResponseEntity<Void> deleteEvaluationEntity(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.deleteEvaluationEntity(evaluationDTO);
	}
	
	@DeleteMapping(value = "delete/season")
	public ResponseEntity<Void> deleteEvaluationSeason(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.deleteEvaluationSeason(evaluationDTO);
	}
	
	@DeleteMapping(value = "delete/episode")
	public ResponseEntity<Void> deleteEvaluationEpisode(@RequestBody EvaluationDTO evaluationDTO){
		return evaluationService.deleteEvaluationEpisode(evaluationDTO);
	}
}
