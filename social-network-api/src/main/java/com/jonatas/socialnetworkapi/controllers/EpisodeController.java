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
import com.jonatas.socialnetworkapi.dto.EpisodeDTO;
import com.jonatas.socialnetworkapi.services.EpisodeService;

@RestController
@RequestMapping(value = "/episodes")
public class EpisodeController {

	@Autowired
	private EpisodeService episodeService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return episodeService.findAll();
	}
	
	@GetMapping(value = "get/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable String id){
		return episodeService.findById(id);
	}
	
	@GetMapping(value = "get/evaluations/{id}")
	public ResponseEntity<Object> getEvaluationsEntity(@PathVariable String id){
		return episodeService.getEvaluationsEpisode(id);
	}
	
	@GetMapping(value = "get/editions/{id}")
	public ResponseEntity<Object> getEditions(@PathVariable String id){
		return episodeService.getEditions(id);
	}
	
	//post
	
	@PostMapping(value = "post/user/{idUser}/season/{idSeason}")
	public ResponseEntity<Object> newEpisode(@RequestBody EpisodeDTO episodeDTO, @PathVariable String idUser, @PathVariable String idSeason){
		return episodeService.newEpisode(episodeDTO, idUser, idSeason);
	}
	
	//put
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(@RequestBody EditionDTO editionDTO){
		return episodeService.updateName(editionDTO);
	}
	
	@PutMapping(value = "put/image")
	public ResponseEntity<Void> updateImage(@RequestBody EditionDTO editionDTO){
		return episodeService.updateImage(editionDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(@RequestBody EditionDTO editionDTO){
		return episodeService.updateDescription(editionDTO);
	}
	
	@PutMapping(value = "put/release")
	public ResponseEntity<Void> updateRelease(@RequestBody EditionDTO editionDTO){
		return episodeService.updateRelease(editionDTO);
	}
	
	@PutMapping(value = "put/genre")
	public ResponseEntity<Void> updateGenre(@RequestBody EditionDTO editionDTO){
		return episodeService.updateGenre(editionDTO);
	}

}
