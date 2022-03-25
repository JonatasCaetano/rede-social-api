package com.jonatas.socialnetworkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.dto.GroupDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.GroupMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.services.GroupService;

@RestController
@RequestMapping(value = "group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	//get
	
	@GetMapping(value = "/{idGroup}/{idUser}")
	public ResponseEntity<GroupMiniDTO> getGroup(@PathVariable String idGroup, @PathVariable String idUser){
		try {
			//GroupMiniDTO groupMiniDTO = groupService.getGroup(idGroup, idUser);
			return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroup(idGroup, idUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping(value = "/{idGroup}/members")
	public ResponseEntity<List<UserMiniDTO>> getMembers(@PathVariable String idGroup){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(groupService.getMembers(idGroup));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping(value = "/{idGroup}/moderators")
	public ResponseEntity<List<UserMiniDTO>> getModerators(@PathVariable String idGroup){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(groupService.getModerators(idGroup));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//post
	
	@PostMapping
	public ResponseEntity<GroupMiniDTO> createGroup(@RequestBody GroupDTO groupDTO){
		try {
			//GroupMiniDTO groupMiniDTO = groupService.createGroup(groupDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(groupDTO));
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	//put
	
	@PutMapping(value = "{idGroup}/add/{idUser}")
	public ResponseEntity<GroupMiniDTO> enterGroup(@PathVariable String idGroup, @PathVariable String idUser){
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(groupService.enterGroup(idGroup, idUser));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "{idGroup}/{idCreator}/add/moderator/{idMember}")
	public ResponseEntity<GroupMiniDTO> addModerator(@PathVariable String idGroup, @PathVariable String idCreator, @PathVariable String idMember){
		try {
			boolean added = groupService.addModerator(idGroup, idCreator, idMember);
			if(added) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "{idGroup}/{idCreator}/remove/moderator/{idMember}")
	public ResponseEntity<GroupMiniDTO> removeModerator(@PathVariable String idGroup, @PathVariable String idCreator, @PathVariable String idMember){
		try {
			boolean added = groupService.removeModerator(idGroup, idCreator, idMember);
			if(added) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
