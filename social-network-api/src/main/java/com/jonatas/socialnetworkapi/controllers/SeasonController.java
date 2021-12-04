package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.services.SeasonService;

@RestController
@RequestMapping(value = "/seasons")
public class SeasonController {

	@Autowired
	private SeasonService seasonService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<List<SeasonDTO>> findAll(){
		return seasonService.findAll();
	}
	
	//post
	
	@PostMapping(value = "post/{id}")
	public ResponseEntity<Season> newSeason(@RequestBody SeasonDTO seasonDTO, @PathVariable String id){
		return seasonService.newSeason(seasonDTO, id);
	}
}