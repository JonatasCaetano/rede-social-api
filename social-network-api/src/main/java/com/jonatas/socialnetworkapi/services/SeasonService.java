package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.SeasonRepository;

@Service
public class SeasonService {

	@Autowired
	private SeasonRepository seasonRepository;
}
