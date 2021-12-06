package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EvaluationEntityDTO;
import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.dto.mini.EpisodeMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.SeasonMiniDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;

@Service
public class SeasonService {

	//repositories
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private EntityService entityService;
	
	//methods
	
	public ResponseEntity<Object> findAll(){
		List<Season> list = seasonRepository.findAll();
		List<SeasonMiniDTO> seasonMiniDTOs = new ArrayList<>();
		for(Season season : list) {
			SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
			seasonMiniDTOs.add(seasonMiniDTO);
		}
		return ResponseEntity.ok().body(seasonMiniDTOs);
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			return ResponseEntity.ok().body(season);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> save(Season season){
		try {
			Season obj = seasonRepository.save(season);
			return ResponseEntity.accepted().body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> newSeason(SeasonDTO seasonDTO, String idUser, String idEntity){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Entity entity = (Entity) entityService.findById(idEntity).getBody();
			Season season = new Season(seasonDTO);
			List<Season> seasons = entity.getSeasons();
			if(user.isChecked()) {
				if(seasons.contains(season)) {
					return ResponseEntity.badRequest().build();
				}
					try {
						season.setEntity(entity);
						Season obj = seasonRepository.insert(season);
						entity.getSeasons().add(obj);
						entity.setSeason(1);
						entityService.save(entity);
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
	
	public ResponseEntity<Object> getEvaluationsSeason(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			List<Evaluation> evaluations = season.getEvaluations();
			List<EvaluationEntityDTO> evaluationEntityDTOs = new ArrayList<>();
			for(Evaluation evaluation : evaluations) {
				EvaluationEntityDTO evaluationEntityDTO = new EvaluationEntityDTO(evaluation);
				evaluationEntityDTOs.add(evaluationEntityDTO);
			}
			return ResponseEntity.ok().body(evaluationEntityDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findAllEpisodes(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			List<Episode> episodes = season.getEpisodes();
			List<EpisodeMiniDTO> episodeMiniDTOs = new ArrayList<>();
			for(Episode episode : episodes) {
				EpisodeMiniDTO episodeMiniDTO = new EpisodeMiniDTO(episode);
				episodeMiniDTOs.add(episodeMiniDTO);
			}
			return ResponseEntity.ok().body(episodeMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
