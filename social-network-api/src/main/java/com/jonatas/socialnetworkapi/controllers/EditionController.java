package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.services.EditionService;

@RestController
@RequestMapping(value = "/editions")
public class EditionController {

	@Autowired
	private EditionService editionService;
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return editionService.findAll();
	}
}
