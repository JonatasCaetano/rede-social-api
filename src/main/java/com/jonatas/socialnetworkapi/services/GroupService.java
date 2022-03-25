package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Group;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.GroupDTO;
import com.jonatas.socialnetworkapi.repositories.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	@Lazy
	private UserService userService;
	
	
	//post
	
	public Group createGroup(GroupDTO groupDTO) {
		User user = (User) userService.findById(groupDTO.getIdCreator()).getBody();
		Group group = new Group(groupDTO.getName(), groupDTO.getDescription(), user, groupDTO.getCreationDate());
		return groupRepository.insert(group);
	}
}
