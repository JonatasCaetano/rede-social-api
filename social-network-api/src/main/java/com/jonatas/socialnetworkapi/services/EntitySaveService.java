package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EntitySaveDTO;
import com.jonatas.socialnetworkapi.dto.EvaluationDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.EntitySaveRepository;

@Service
public class EntitySaveService {

	//repositories
	
	@Autowired
	private EntitySaveRepository entitySaveRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private EntityService entityService;
	
	@Autowired
	@Lazy
	private SeasonService seasonService;
	
	@Autowired
	@Lazy
	private EpisodeService episodeService;
	
	@Autowired
	@Lazy
	private EvaluationService evaluationService;

	//methods
	
	public ResponseEntity<Object> findAll(){
		try {
			List<EntitySave> list = entitySaveRepository.findAll();
			return ResponseEntity.ok(list);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			EntitySave entitySave = entitySaveRepository.findById(id).get();
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newEntitySaveEntity(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getUser()).getBody();
			Entity entity = (Entity) entityService.findById(entitySaveDTO.getEntity()).getBody();
			EntitySave entitySave = new EntitySave(
					user,
					entity,
					null,
					null,
					entitySaveDTO.getCategory(),
					entitySaveDTO.getCategory() == 5 ? true : false,
					entitySaveDTO.getCategory() == 6 ? true : false,
					entitySaveDTO.getCategory() == 7 ? true : false,
					0
					);
			entitySave = entitySaveRepository.insert(entitySave);
			user.getEntitySaves().add(entitySave);
			userService.save(user);
			entity.getEntitySaves().add(entitySave);
			entityService.save(entity);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newEntitySaveSeason(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getUser()).getBody();
			Season season = (Season) seasonService.findById(entitySaveDTO.getSeason()).getBody();
			EntitySave entitySave = new EntitySave(
					user,
					null,
					season,
					null,
					entitySaveDTO.getCategory(),
					entitySaveDTO.getCategory() == 5 ? true : false,
					entitySaveDTO.getCategory() == 6 ? true : false,
					entitySaveDTO.getCategory() == 7 ? true : false,
					1
					);
			entitySave = entitySaveRepository.insert(entitySave);
			user.getEntitySaves().add(entitySave);
			userService.save(user);
			season.getEntitySaves().add(entitySave);
			seasonService.save(season);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newEntitySaveEpisode(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getUser()).getBody();
			Episode episode = (Episode) episodeService.findById(entitySaveDTO.getEpisode()).getBody();
			EntitySave entitySave = new EntitySave(
					user,
					null,
					null,
					episode,
					entitySaveDTO.getCategory(),
					entitySaveDTO.getCategory() == 5 ? true : false,
					entitySaveDTO.getCategory() == 6 ? true : false,
					entitySaveDTO.getCategory() == 7 ? true : false,
					2
					);
			entitySave = entitySaveRepository.insert(entitySave);
			user.getEntitySaves().add(entitySave);
			userService.save(user);
			episode.getEntitySaves().add(entitySave);
			episodeService.save(episode);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> updateEntitySave(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			entitySave.setCategory(entitySaveDTO.getCategory());
			switch (entitySaveDTO.getCategory()) {
			case 5: 
				entitySave.setGoal(true);
				break;
			case 6: 
				entitySave.setRated(true);
				break;
			case 7: 
				entitySave.setReview(true);
				break;	
			}			
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> deleteEntitySaveEntity(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getUser()).getBody();
			Entity entity = (Entity) entityService.findById(entitySaveDTO.getEntity()).getBody();
			List<Evaluation> evaluations = entity.getEvaluations();
			for(Evaluation evaluation : evaluations) {
				if(evaluation.getUser().getId().hashCode() == user.getId().hashCode()) {
					entity.getEvaluations().remove(evaluation);
					entityService.save(entity);
					user.getEvaluations().remove(evaluation);
					userService.save(user);
					evaluationService.deleteEvaluation(new EvaluationDTO(evaluation));
				}
			}
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			user.getEntitySaves().remove(entitySave);
			userService.save(user);
			entity.getEntitySaves().remove(entitySave);
			entityService.save(entity);
			entitySaveRepository.delete(entitySave);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> deleteEntitySaveSeason(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getUser()).getBody();
			Season season = (Season) seasonService.findById(entitySaveDTO.getSeason()).getBody();
			List<Evaluation> evaluations = season.getEvaluations();
			for(Evaluation evaluation : evaluations) {
				if(evaluation.getUser().getId().hashCode() == user.getId().hashCode()) {
					season.getEvaluations().remove(evaluation);
					seasonService.save(season);
					user.getEvaluations().remove(evaluation);
					userService.save(user);
					evaluationService.deleteEvaluation(new EvaluationDTO(evaluation));
				}
			}
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			user.getEntitySaves().remove(entitySave);
			userService.save(user);
			season.getEntitySaves().remove(entitySave);
			seasonService.save(season);
			entitySaveRepository.delete(entitySave);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> deleteEntitySaveEpisode(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getUser()).getBody();
			Episode episode = (Episode) entityService.findById(entitySaveDTO.getEpisode()).getBody();
			List<Evaluation> evaluations = episode.getEvaluations();
			for(Evaluation evaluation : evaluations) {
				if(evaluation.getUser().getId().hashCode() == user.getId().hashCode()) {
					episode.getEvaluations().remove(evaluation);
					episodeService.save(episode);
					user.getEvaluations().remove(evaluation);
					userService.save(user);
					evaluationService.deleteEvaluation(new EvaluationDTO(evaluation));
				}
			}
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			user.getEntitySaves().remove(entitySave);
			userService.save(user);
			episode.getEntitySaves().remove(entitySave);
			episodeService.save(episode);
			entitySaveRepository.delete(entitySave);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
