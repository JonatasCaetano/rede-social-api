package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping(value = "get/evaluations/{id}")
	public ResponseEntity<Object> getEvaluationsEntity(@PathVariable String id){
		return episodeService.getEvaluationsEpisode(id);
	}
	
	//post
	
	@PostMapping(value = "post/user/{idUser}/season/{idSeason}")
	public ResponseEntity<Object> newEpisode(@RequestBody EpisodeDTO episodeDTO, @PathVariable String idUser, @PathVariable String idSeason){
		return episodeService.newEpisode(episodeDTO, idUser, idSeason);
	}
}
