package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Edition;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.EditionDTO;
import com.jonatas.socialnetworkapi.entities.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EditionMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EpisodeMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EvaluationMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.SeasonMiniDTO;
import com.jonatas.socialnetworkapi.enuns.TypeEdition;
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
	
	//get
	
	public ResponseEntity<Object> findAllMini(){
		try {
			List<Season> list = seasonRepository.findAll();
			List<SeasonMiniDTO> seasonMiniDTOs = new ArrayList<>();
			for(Season season : list) {
				SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
				seasonMiniDTOs.add(seasonMiniDTO);
			}
			return ResponseEntity.ok().body(seasonMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByName(String name){
		try {
			List<Season> list = seasonRepository.searchByName(name);
			List<SeasonMiniDTO> seasonMiniDTOs = new ArrayList<>();
			for(Season season : list) {
				SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
				seasonMiniDTOs.add(seasonMiniDTO);
			}
			return ResponseEntity.ok().body(seasonMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
			return ResponseEntity.ok().body(seasonMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
		
	public ResponseEntity<Object> getEvaluationsMini(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			List<Evaluation> evaluations = new ArrayList<>();
			List<EntitySave> entitySaves = season.getEntitySaves();
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.isRated()) {
					evaluations.add(entitySave.getEvaluation());
				}
			}
			List<EvaluationMiniDTO> evaluationMiniDTOs = new ArrayList<>();
			for(Evaluation evaluation : evaluations) {
				EvaluationMiniDTO evaluationMiniDTO = new EvaluationMiniDTO(evaluation);
				evaluationMiniDTOs.add(evaluationMiniDTO);
			}
			return ResponseEntity.ok().body(evaluationMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getEpisodesMini(String id){
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
	
	public ResponseEntity<Object> getEditionsMini(String id){
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
	
	//post
	
	public ResponseEntity<Object> newSeason(SeasonDTO seasonCreateDTO, String idUser, String idEntity){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Entity entity = (Entity) entityService.findById(idEntity).getBody();
			Season season = new Season(seasonCreateDTO);
			List<Season> seasons = entity.getSeasons();
			if(user.isChecked()) {
				//hashCode and equals
				if(seasons.contains(season)) {
					return ResponseEntity.badRequest().build();
				}
					try {
						season.setEntity(entity);
						season = seasonRepository.insert(season);
						entity.getSeasons().add(season);
						entity.setSeason(1);
						entityService.save(entity);
						SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
						return ResponseEntity.created(null).body(seasonMiniDTO);
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
	
	
	//put
	
	public ResponseEntity<Void> updateName(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Season season = seasonRepository.findById(editionDTO.getSeason()).get();
			editionDTO.setTypeEdition(TypeEdition.SEASON);
			editionDTO.setAttribute("name");
			editionDTO.setPrevious(season.getName());
			season.setName((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
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
			editionDTO.setPrevious(season.getImage());
			season.setImage((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
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
			editionDTO.setPrevious(season.getDescription());
			season.setDescription((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
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
			editionDTO.setPrevious(season.getRelease());
			season.setRelease((Date) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
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
			editionDTO.setPrevious(season.getGenre());
			season.setGenre((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			edition = (Edition) editionService.newEdition(edition).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	//internal
	
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
	
	
	
}
