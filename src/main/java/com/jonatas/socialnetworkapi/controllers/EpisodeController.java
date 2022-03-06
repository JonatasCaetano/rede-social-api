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
import com.jonatas.socialnetworkapi.entities.dto.EpisodeDTO;
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
	
	@GetMapping(value = "get/episode/{id}/entitysaves")
	public ResponseEntity<Object> getAllEntitySaveMini(@PathVariable String id){
		return episodeService.getAllEntitySaveMini(id);
	}
	
	@GetMapping(value = "get/episode/{idEpisode}/entitysave/user/{idUser}")
	public ResponseEntity<Object> getEntitySaveMini(@PathVariable String idEpisode, @PathVariable String idUser ){
		return episodeService.getEntitySaveMini(idEpisode, idUser);
	}
	
	@GetMapping(value = "get/episode/{id}/editions")
	public ResponseEntity<Object> getEditionsMini(@PathVariable String id){
		return episodeService.getEditionsMini(id);
	}
	
	@GetMapping(value = "get/episodes/name")
	public ResponseEntity<Object> findByName(@RequestParam(value = "name", defaultValue = "") String name){
		return episodeService.findByName(name);
	}
	
	@GetMapping(value = "get/reviews/{idEpisode}/user/{idUser}")
	public ResponseEntity<Object> getReviewMini(@PathVariable String idEpisode, @PathVariable String idUser){
		return episodeService.getReviewMini(idEpisode, idUser);
	}
	
	//post
	
	@PostMapping(value = "post/season/{idSeason}/user/{idUser}")
	public ResponseEntity<Object> newEpisode(@RequestBody EpisodeDTO episodeCreateDTO, @PathVariable String idUser, @PathVariable String idSeason){
		return episodeService.newEpisode(episodeCreateDTO, idUser, idSeason);
	}
	
	//put
	
	@PutMapping(value = "put/name")
	public ResponseEntity<Void> updateName(@RequestBody EditionDTO editionDTO){
		return episodeService.updateName(editionDTO);
	}
	
	@PutMapping(value = "put/add/image")
	public ResponseEntity<Void> updateImage(@RequestBody EditionDTO editionDTO){
		return episodeService.addImages(editionDTO);
	}
	
	@PutMapping(value = "put/remove/image")
	public ResponseEntity<Void> removeImages(@RequestBody EditionDTO editionDTO){
		return episodeService.removeImages(editionDTO);
	}
	
	@PutMapping(value = "put/description")
	public ResponseEntity<Void> updateDescription(@RequestBody EditionDTO editionDTO){
		return episodeService.updateDescription(editionDTO);
	}
	
}
