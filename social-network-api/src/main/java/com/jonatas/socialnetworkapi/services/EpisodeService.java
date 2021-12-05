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

@Service
public class EpisodeService {

	//repositories
	
	@Autowired
	private EpisodeRepository episodeRepository;
	
	//services
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SeasonService seasonService;
	
	//methods
	
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
			User user = userService.findById(idUser).getBody();
			Season season = seasonService.findById(idSeason).getBody();
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
					seasonService.save(season);
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
