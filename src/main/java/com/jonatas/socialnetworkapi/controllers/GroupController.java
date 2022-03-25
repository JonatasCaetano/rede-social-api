package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.entities.Group;
import com.jonatas.socialnetworkapi.entities.dto.GroupDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.GroupMiniDTO;
import com.jonatas.socialnetworkapi.services.GroupService;

@RestController
@RequestMapping(value = "group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@PostMapping
	public ResponseEntity<GroupMiniDTO> createGroup(@RequestBody GroupDTO groupDTO){
		try {
			Group group = groupService.createGroup(groupDTO);
			GroupMiniDTO groupMiniDTO = new GroupMiniDTO(group);
			return ResponseEntity.status(HttpStatus.CREATED).body(groupMiniDTO);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
