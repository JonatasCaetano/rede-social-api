package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Group;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.GroupDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.GroupMiniDTO;
import com.jonatas.socialnetworkapi.repositories.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	@Lazy
	private UserService userService;
	
	//get
	
	public GroupMiniDTO getGroup(String idGroup, String idUser) {
		User user = (User) userService.findById(idUser).getBody();
		Group group = groupRepository.findById(idGroup).get();
		GroupMiniDTO groupMiniDTO = new GroupMiniDTO(group);
		if(group.getMembers().contains(user) || group.getCreator().equals(user)) {
			groupMiniDTO.setUserIsMember(true);
		}else {
			groupMiniDTO.setUserIsMember(false);
		}
		return groupMiniDTO;
	}
	
	
	//post
	
	public GroupMiniDTO createGroup(GroupDTO groupDTO) {
		User user = (User) userService.findById(groupDTO.getIdCreator()).getBody();
		Group group = new Group(groupDTO.getName(), groupDTO.getDescription(), user, groupDTO.getCreationDate());
		group = groupRepository.insert(group);
		GroupMiniDTO groupMiniDTO = new GroupMiniDTO(group);
		groupMiniDTO.setUserIsMember(true);
		return groupMiniDTO;
	}
}
