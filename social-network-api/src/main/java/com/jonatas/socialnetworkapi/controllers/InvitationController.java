package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.services.InvitationService;

@RestController
@RequestMapping(value = "/invitations")
public class InvitationController {
	
	@Autowired
	private InvitationService invitationService;
	
	//get
	
	@GetMapping(value = "get/all")
	public ResponseEntity<Object> findAll(){
		return invitationService.findAll();
	}
	
	@GetMapping(value = "get/check/{invitationValue}")
	public ResponseEntity<Boolean> checkAvailability(@PathVariable String invitationValue){
		return invitationService.checkAvailability(invitationValue);
	}
	
	//post
	
	@PostMapping(value = "post/invitation/{value}")
	public ResponseEntity<Object> findByValue(@PathVariable String value){
		return invitationService.findByValue(value);
	}
	


}
