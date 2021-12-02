package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.services.InvitationService;

@RestController
@RequestMapping(value = "/invitations")
public class InvitationController {
	
	@Autowired
	private InvitationService invitationService;
	
	@GetMapping(value = "get/all")
	public ResponseEntity<List<Invitation>> findAll(){
		return invitationService.findAll();
	}
	
	@GetMapping(value = "get/invitation/{value}")
	public ResponseEntity<Invitation> findByValue(@PathVariable String value){
		return invitationService.findByValue(value);
	}
	
	@GetMapping(value = "get/check/{invitationValue}")
	public ResponseEntity<Void> checkAvailability(@PathVariable String invitationValue){
		return invitationService.checkAvailability(invitationValue);
	}


}
