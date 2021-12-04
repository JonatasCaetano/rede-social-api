package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EpisodeDTO;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.repositories.EpisodeRepository;

@Service
public class EpisodeService {

	@Autowired
	private EpisodeRepository episodeRepository;
	
	public ResponseEntity<List<EpisodeDTO>> findAll(){
		List<Episode> episodes = episodeRepository.findAll();
		List<EpisodeDTO> episodeDTOs = new ArrayList<>();
		for(Episode episode : episodes) {
			EpisodeDTO episodeDTO = new EpisodeDTO(episode);
			episodeDTOs.add(episodeDTO);
		}
		return ResponseEntity.ok().body(episodeDTOs);
	}
}
