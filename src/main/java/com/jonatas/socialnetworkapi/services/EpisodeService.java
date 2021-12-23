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
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.EditionDTO;
import com.jonatas.socialnetworkapi.entities.dto.EpisodeDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EditionMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EpisodeMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EvaluationMiniDTO;
import com.jonatas.socialnetworkapi.enuns.TypeEdition;
import com.jonatas.socialnetworkapi.repositories.EpisodeRepository;

@Service
public class EpisodeService {

	//repositories
	
	@Autowired
	private EpisodeRepository episodeRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private SeasonService seasonService;
	
	@Autowired
	@Lazy
	private EditionService editionService;
	
	//methods
		
	public ResponseEntity<Object> findAllMini(){
		try {
			List<Episode> list = episodeRepository.findAll();
			List<EpisodeMiniDTO> episodeMiniDTOs = new ArrayList<>();
			for(Episode episode : list) {
				EpisodeMiniDTO episodeMiniDTO = new EpisodeMiniDTO(episode);
				episodeMiniDTOs.add(episodeMiniDTO);
			}
			return ResponseEntity.ok().body(episodeMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByName(String name){
		try {
			List<Episode> list = episodeRepository.searchByName(name);
			List<EpisodeMiniDTO> episodeMiniDTOs = new ArrayList<>();
			for(Episode episode : list) {
				EpisodeMiniDTO episodeMiniDTO = new EpisodeMiniDTO(episode);
				episodeMiniDTOs.add(episodeMiniDTO);
			}
			return ResponseEntity.ok().body(episodeMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Episode episode = episodeRepository.findById(id).get();
			EpisodeMiniDTO episodeMiniDTO = new EpisodeMiniDTO(episode);
			return ResponseEntity.ok().body(episodeMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
		
	public ResponseEntity<Object> getEvaluationsMini(String id){
		try {
			Episode episode = episodeRepository.findById(id).get();
			List<Evaluation> evaluations = new ArrayList<>();
			List<EntitySave> entitySaves = episode.getEntitySaves();
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
	
	public ResponseEntity<Object> getEditionsMini(String id){
		try {
			Episode episode = episodeRepository.findById(id).get();
			List<Edition> editions = episode.getEditions();
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
	
	public ResponseEntity<Object> newEpisode(EpisodeDTO episodeCreateDTO, String idUser, String idSeason){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Season season = (Season) seasonService.findById(idSeason).getBody();
			Episode episode = new Episode(episodeCreateDTO);
			List<Episode> episodes = season.getEpisodes();
			if(user.isChecked()) {
				if(episodes.contains(episode)) {
					return ResponseEntity.badRequest().build();
				}
				try {
					episode.setSeason(season);
					Episode obj = episodeRepository.insert(episode);
					season.getEpisodes().add(obj);
					season.setEpisode(1);
					seasonService.save(season);
					EpisodeMiniDTO episodeMiniDTO = new EpisodeMiniDTO(obj);
					return ResponseEntity.created(null).body(episodeMiniDTO);
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
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Episode episode = episodeRepository.findById(editionDTO.getIdEpisode()).get();
			editionDTO.setTypeEdition(TypeEdition.EPISODE);
			editionDTO.setAttribute("name");
			editionDTO.setPrevious(episode.getName());
			episode.setName((String) editionDTO.getCurrent());
			episodeRepository.save(episode);
			Edition edition = new Edition(user, null, null, episode, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			episode.getEditions().add(edition);
			episodeRepository.save(episode);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateImage(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Episode episode = episodeRepository.findById(editionDTO.getIdEpisode()).get();
			editionDTO.setTypeEdition(TypeEdition.EPISODE);
			editionDTO.setAttribute("image");
			editionDTO.setPrevious(episode.getImage());
			episode.setImage((String) editionDTO.getCurrent());
			episodeRepository.save(episode);
			Edition edition = new Edition(user, null, null, episode, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			episode.getEditions().add(edition);
			episodeRepository.save(episode);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateDescription(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Episode episode = episodeRepository.findById(editionDTO.getIdEpisode()).get();
			editionDTO.setTypeEdition(TypeEdition.EPISODE);
			editionDTO.setAttribute("description");
			editionDTO.setPrevious(episode.getDescription());
			episode.setDescription((String) editionDTO.getCurrent());
			episodeRepository.save(episode);
			Edition edition = new Edition(user, null, null, episode, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			episode.getEditions().add(edition);
			episodeRepository.save(episode);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateRelease(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Episode episode = episodeRepository.findById(editionDTO.getIdEpisode()).get();
			editionDTO.setTypeEdition(TypeEdition.EPISODE);
			editionDTO.setAttribute("release");
			editionDTO.setPrevious(episode.getRelease());
			episode.setRelease((Date) editionDTO.getCurrent());
			episodeRepository.save(episode);
			Edition edition = new Edition(user, null, null, episode, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			episode.getEditions().add(edition);
			episodeRepository.save(episode);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	public ResponseEntity<Void> updateGenre(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Episode episode = episodeRepository.findById(editionDTO.getIdEpisode()).get();
			editionDTO.setTypeEdition(TypeEdition.EPISODE);
			editionDTO.setAttribute("genre");
			editionDTO.setPrevious(episode.getGenre());
			episode.setGenre((String) editionDTO.getCurrent());
			episodeRepository.save(episode);
			Edition edition = new Edition(user, null, null, episode, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			episode.getEditions().add(edition);
			episodeRepository.save(episode);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//internal
	
	public ResponseEntity<Object> findById(String id){
		try {
			Episode episode = episodeRepository.findById(id).get();
			return ResponseEntity.ok().body(episode);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> save(Episode episode){
		try {
			episodeRepository.save(episode);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
