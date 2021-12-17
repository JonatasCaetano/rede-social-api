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
import com.jonatas.socialnetworkapi.dto.SeasonCreateDTO;
import com.jonatas.socialnetworkapi.services.SeasonService;

@RestController
@RequestMapping(value = "/seasons")
public class SeasonController {

	//services
	
	@Autowired
	private SeasonService seasonService;
	
	//get
	
	@GetMapping(value = "get/seasons")
	public ResponseEntity<Object> findAllMini(){
		return seasonService.findAllMini();
	}
	
	@GetMapping(value = "get/season/{id}")
	public ResponseEntity<Object> findByIdMini(@PathVariable String id){
		return seasonService.findByIdMini(id);
	}
	
	@GetMapping(value = "get/season/{id}/evaluations")
	public ResponseEntity<Object> getEvaluationsMini(@PathVariable String id){
		return seasonService.getEvaluationsMini(id);
	}
	
	@GetMapping(value = "get/season/{id}/episodes")
	public ResponseEntity<Object> getEpisodesMini(@PathVariable String id){
		return seasonService.getEpisodesMini(id);
	}
	
	@GetMapping(value = "get/season/{id}/editions")
	public ResponseEntity<Object> getEditionsMini(@PathVariable String id){
		return seasonService.getEditionsMini(id);
	}
	
	//post
	
	@PostMapping(value = "post/entity/{idEntity}/user/{idUser}")
	public ResponseEntity<Object> newSeason(@RequestBody SeasonCreateDTO seasonCreateDTO, @PathVariable String idUser, @PathVariable String idEntity){
		return seasonService.newSeason(seasonCreateDTO, idUser, idEntity);
	}
	
	//put
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(@RequestBody EditionDTO editionDTO){
		return seasonService.updateName(editionDTO);
	}
	
	@PutMapping(value = "put/image")
	public ResponseEntity<Void> updateImage(@RequestBody EditionDTO editionDTO){
		return seasonService.updateImage(editionDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(@RequestBody EditionDTO editionDTO){
		return seasonService.updateDescription(editionDTO);
	}
	
	@PutMapping(value = "put/release")
	public ResponseEntity<Void> updateRelease(@RequestBody EditionDTO editionDTO){
		return seasonService.updateRelease(editionDTO);
	}
	
	@PutMapping(value = "put/genre")
	public ResponseEntity<Void> updateGenre(@RequestBody EditionDTO editionDTO){
		return seasonService.updateGenre(editionDTO);
	}

}