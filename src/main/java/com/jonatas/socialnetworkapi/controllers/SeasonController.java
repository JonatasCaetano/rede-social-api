package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.dto.EditionDTO;
import com.jonatas.socialnetworkapi.entities.dto.SeasonDTO;
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
	
	@GetMapping(value = "get/season/{id}/entitysaves")
	public ResponseEntity<Object> getEvaluationsMini(@PathVariable String id){
		return seasonService.getAllEntitySaveMini(id);
	}
	
	@GetMapping(value = "get/season/{idSeason}/entitysave/user/{idUser}")
	public ResponseEntity<Object> getEvaluationsMini(@PathVariable String idSeason, @PathVariable String idUser ){
		return seasonService.getEntitySaveMini(idSeason, idUser);
	}
	
	@GetMapping(value = "get/season/{id}/episodes")
	public ResponseEntity<Object> getEpisodesMini(@PathVariable String id){
		return seasonService.getEpisodesMini(id);
	}
	
	@GetMapping(value = "get/season/{id}/editions")
	public ResponseEntity<Object> getEditionsMini(@PathVariable String id){
		return seasonService.getEditionsMini(id);
	}
	
	@GetMapping(value = "get/seasons/name")
	public ResponseEntity<Object> findByName(@RequestParam(value = "name", defaultValue = "") String name){
		return seasonService.findByName(name);
	}
	
	//post
	
	@PostMapping(value = "post/entity/{idEntity}/user/{idUser}")
	public ResponseEntity<Object> newSeason(@RequestBody SeasonDTO seasonDTO, @PathVariable String idUser, @PathVariable String idEntity){
		return seasonService.newSeason(seasonDTO, idUser, idEntity);
	}
	
	//put
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(@RequestBody EditionDTO editionDTO){
		return seasonService.updateName(editionDTO);
	}
	
	@PutMapping(value = "put/add/image")
	public ResponseEntity<Void> updateImage(@RequestBody EditionDTO editionDTO){
		return seasonService.addImages(editionDTO);
	}
	
	@PutMapping(value = "put/remove/image")
	public ResponseEntity<Void> removeImages(@RequestBody EditionDTO editionDTO){
		return seasonService.removeImages(editionDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(@RequestBody EditionDTO editionDTO){
		return seasonService.updateDescription(editionDTO);
	}
}
