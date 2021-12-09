package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EditionDTO;
import com.jonatas.socialnetworkapi.dto.EvaluationEntityDTO;
import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.dto.mini.EditionMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.EpisodeMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.SeasonMiniDTO;
import com.jonatas.socialnetworkapi.entities.Edition;
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
	
	@Autowired
	@Lazy
	private EditionService editionService;
	
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
	
	public ResponseEntity<Object> getEditions(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			List<Edition> editions = season.getEditions();
			List<EditionMiniDTO> editionMiniDTOs = new ArrayList<>();
			for(Edition edition : editions) {
				EditionMiniDTO editionMiniDTO = new EditionMiniDTO(edition);
				editionMiniDTOs.add(editionMiniDTO);
			}
			return ResponseEntity.ok().body(editionMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	//put
	
	public ResponseEntity<Void> updateName(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Season season = seasonRepository.findById(editionDTO.getSeason()).get();
			editionDTO.setAttribute("name");
			editionDTO.setPrevius(season.getName());
			season.setName((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateImage(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Season season = seasonRepository.findById(editionDTO.getSeason()).get();
			editionDTO.setAttribute("image");
			editionDTO.setPrevius(season.getImage());
			season.setImage((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateDescription(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Season season = seasonRepository.findById(editionDTO.getSeason()).get();
			editionDTO.setAttribute("description");
			editionDTO.setPrevius(season.getDescription());
			season.setDescription((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateRelease(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Season season = seasonRepository.findById(editionDTO.getSeason()).get();
			editionDTO.setAttribute("release");
			editionDTO.setPrevius(season.getRelease());
			season.setRelease((Date) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateGenre(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Season season = seasonRepository.findById(editionDTO.getSeason()).get();
			editionDTO.setAttribute("genre");
			editionDTO.setPrevius(season.getGenre());
			season.setGenre((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	
}
