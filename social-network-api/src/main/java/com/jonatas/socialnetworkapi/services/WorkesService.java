package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.WorkersRepository;

@Service
public class WorkesService {

	@Autowired
	private WorkersRepository workersRepository;
}
