package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.EntityRepository;

@Service
public class EntityService {

	@Autowired
	private EntityRepository entityRepository;
}
