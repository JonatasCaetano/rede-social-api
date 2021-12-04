package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.EpisodeRepository;

@Service
public class EpisodeService {

	@Autowired
	private EpisodeRepository episodeRepository;
}
