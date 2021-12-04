package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EpisodeDTO;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.EpisodeRepository;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class EpisodeService {

	@Autowired
	private EpisodeRepository episodeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	public ResponseEntity<List<EpisodeDTO>> findAll(){
		List<Episode> episodes = episodeRepository.findAll();
		List<EpisodeDTO> episodeDTOs = new ArrayList<>();
		for(Episode episode : episodes) {
			EpisodeDTO episodeDTO = new EpisodeDTO(episode);
			episodeDTOs.add(episodeDTO);
		}
		return ResponseEntity.ok().body(episodeDTOs);
	}
	
	public ResponseEntity<Episode> newEpisode(EpisodeDTO episodeDTO, String idUser, String idSeason){
		try {
			User user = userRepository.findById(idUser).get();
			Season season = seasonRepository.findById(idSeason).get();
			Episode episode = new Episode(episodeDTO);
			List<Episode> episodes = season.getEpisodes();
			if(user.isChecked()) {
				if(episodes.contains(episode)) {
					return ResponseEntity.badRequest().build();
				}
				try {
					episode.setSeason(season);
					Episode obj = episodeRepository.insert(episode);
					season.getEpisodes().add(obj);
					season.setEpisode(season.getEpisode() + 1);
					seasonRepository.save(season);
					return ResponseEntity.created(null).body(obj);
				}catch(RuntimeException e) {
					return ResponseEntity.badRequest().build();
				}
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
