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

import com.jonatas.socialnetworkapi.dto.EditionDTO;
import com.jonatas.socialnetworkapi.dto.EpisodeCreateDTO;
import com.jonatas.socialnetworkapi.services.EpisodeService;

@RestController
@RequestMapping(value = "/episodes")
public class EpisodeController {

	@Autowired
	private EpisodeService episodeService;
	
	//get
	
	@GetMapping(value = "get/episodes")
	public ResponseEntity<Object> findAllMini(){
		return episodeService.findAllMini();
	}
	
	@GetMapping(value = "get/episode/{id}")
	public ResponseEntity<Object> findByIdMini(@PathVariable String id){
		return episodeService.findByIdMini(id);
	}
	
	@GetMapping(value = "get/episode/{id}/evalutions")
	public ResponseEntity<Object> getEvaluationsMini(@PathVariable String id){
		return episodeService.getEvaluationsMini(id);
	}
	
	@GetMapping(value = "get/episode/{id}/editions")
	public ResponseEntity<Object> getEditionsMini(@PathVariable String id){
		return episodeService.getEditionsMini(id);
	}
	
	@GetMapping(value = "get/episodes/name")
	public ResponseEntity<Object> findByName(@RequestParam(value = "name", defaultValue = "") String name){
		return episodeService.findByName(name);
	}
	
	//post
	
	@PostMapping(value = "post/season/{idSeason}/user/{idUser}")
	public ResponseEntity<Object> newEpisode(@RequestBody EpisodeCreateDTO episodeCreateDTO, @PathVariable String idUser, @PathVariable String idSeason){
		return episodeService.newEpisode(episodeCreateDTO, idUser, idSeason);
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
