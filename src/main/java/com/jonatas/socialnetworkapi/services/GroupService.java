package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
}
