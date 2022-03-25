package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.dto.GroupDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.GroupMiniDTO;
import com.jonatas.socialnetworkapi.services.GroupService;

@RestController
@RequestMapping(value = "group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@GetMapping(value = "/{idGroup}/{idUser}")
	public ResponseEntity<GroupMiniDTO> getGroup(@PathVariable String idGroup, @PathVariable String idUser){
		try {
			GroupMiniDTO groupMiniDTO = groupService.getGroup(idGroup, idUser);
			return ResponseEntity.status(HttpStatus.OK).body(groupMiniDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<GroupMiniDTO> createGroup(@RequestBody GroupDTO groupDTO){
		try {
			GroupMiniDTO groupMiniDTO = groupService.createGroup(groupDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(groupMiniDTO);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
