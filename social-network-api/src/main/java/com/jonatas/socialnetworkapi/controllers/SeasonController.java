package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.EditionDTO;
import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.services.SeasonService;

@RestController
@RequestMapping(value = "/seasons")
public class SeasonController {

	//services
	
	@Autowired
	private SeasonService seasonService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return seasonService.findAll();
	}
	
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return seasonService.findById(id);
	}
	
	@GetMapping(value = "get/evaluations/{id}")
	public ResponseEntity<Object> getEvaluationsEntity(@PathVariable String id){
		return seasonService.getEvaluationsSeason(id);
	}
	
	@GetMapping(value = "get/episodes/{id}")
	public ResponseEntity<Object> findAllEpisodes(@PathVariable String id){
		return seasonService.findAllEpisodes(id);
	}
	
	//post
	
	@PostMapping(value = "post/user/{idUser}/entity/{idEntity}")
	public ResponseEntity<Object> newSeason(@RequestBody SeasonDTO seasonDTO, @PathVariable String idUser, @PathVariable String idEntity){
		return seasonService.newSeason(seasonDTO, idUser, idEntity);
	}
	
	//put
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(EditionDTO editionDTO){
		return seasonService.updateName(editionDTO);
	}
	
	@PutMapping(value = "put/image")
	public ResponseEntity<Void> updateImage(EditionDTO editionDTO){
		return seasonService.updateImage(editionDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(EditionDTO editionDTO){
		return seasonService.updateDescription(editionDTO);
	}
	
	@PutMapping(value = "put/release")
	public ResponseEntity<Void> updateRelease(EditionDTO editionDTO){
		return seasonService.updateRelease(editionDTO);
	}

}
