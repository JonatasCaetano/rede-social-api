package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.EntitySaveDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EntitySaveMiniDTO;
import com.jonatas.socialnetworkapi.enuns.TypeEntitySave;
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
	
	//methods
	
	//get
	
	public ResponseEntity<Object> findAllMini(){
		try {
			List<EntitySave> list = entitySaveRepository.findAll();
			List<EntitySaveMiniDTO> entitySaveMiniDTOs = new ArrayList<>();
			for(EntitySave entitySave : list) {
				EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
				entitySaveMiniDTOs.add(entitySaveMiniDTO);
			}
			return ResponseEntity.ok(entitySaveMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			EntitySave entitySave = entitySaveRepository.findById(id).get();
			EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
			return ResponseEntity.ok(entitySaveMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
	public ResponseEntity<Object> newEntitySaveEntity(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getIdUser()).getBody();
			Entity entity = (Entity) entityService.findById(entitySaveDTO.getIdEntity()).getBody();
			if(entitySaveDTO.getCategory() < 1 || entitySaveDTO.getCategory() > 4) {
				return ResponseEntity.badRequest().build();
			}
			EntitySave entitySave = new EntitySave(
					user,
					entity,
					null,
					null,
					entitySaveDTO.getCategory(),
					TypeEntitySave.ENTITY
					);
			List<EntitySave> entitySaves = user.getEntitySaves();
			for(EntitySave obj : entitySaves) {
				boolean entitySaveExists = false;
				if(obj.getEntity().getId().hashCode() == entity.getId().hashCode()) {
					entitySaveExists = true;
				}
				if(entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			entitySave = entitySaveRepository.insert(entitySave);
			user.getEntitySaves().add(entitySave);
			userService.save(user);
			entity.getEntitySaves().add(entitySave);
			entityService.save(entity);
			EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
			return ResponseEntity.created(null).body(entitySaveMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newEntitySaveSeason(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getIdUser()).getBody();
			Season season = (Season) seasonService.findById(entitySaveDTO.getIdSeason()).getBody();
			EntitySave entitySave = new EntitySave(
					user,
					null,
					season,
					null,
					entitySaveDTO.getCategory(),
					TypeEntitySave.SEASON
					);
			List<EntitySave> entitySaves = user.getEntitySaves();
			for(EntitySave obj : entitySaves) {
				boolean entitySaveExists = false;
				if(obj.getSeason().getId().hashCode() == season.getId().hashCode()) {
					entitySaveExists = true;
				}
				if(entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			entitySave = entitySaveRepository.insert(entitySave);
			user.getEntitySaves().add(entitySave);
			userService.save(user);
			season.getEntitySaves().add(entitySave);
			seasonService.save(season);
			return ResponseEntity.created(null).build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newEntitySaveEpisode(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getIdUser()).getBody();
			Episode episode = (Episode) episodeService.findById(entitySaveDTO.getIdEpisode()).getBody();
			EntitySave entitySave = new EntitySave(
					user,
					null,
					null,
					episode,
					entitySaveDTO.getCategory(),
					TypeEntitySave.EPISODE
					);
			List<EntitySave> entitySaves = user.getEntitySaves();
			for(EntitySave obj : entitySaves) {
				boolean entitySaveExists = false;
				if(obj.getEpisode().getId().hashCode() == episode.getId().hashCode()) {
					entitySaveExists = true;
				}
				if(entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			entitySave = entitySaveRepository.insert(entitySave);
			user.getEntitySaves().add(entitySave);
			userService.save(user);
			episode.getEntitySaves().add(entitySave);
			episodeService.save(episode);
			return ResponseEntity.created(null).build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//put
	
	public ResponseEntity<Object> updateEntitySaveCategory(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
			if(entitySaveDTO.getCategory() < 1 || entitySaveDTO.getCategory() > 4) {
				return ResponseEntity.badRequest().build();
			}
			entitySave.setCategory(entitySaveDTO.getCategory());
			entitySave = entitySaveRepository.save(entitySave);
			EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
			return ResponseEntity.accepted().body(entitySaveMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> updateEntitySaveEvaluation(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
			if(entitySaveDTO.getEvaluation() < 1 || entitySaveDTO.getEvaluation() > 5) {
				return ResponseEntity.badRequest().build();
			}
			switch (entitySave.getTypeEntitySave()) {
			case ENTITY:
				if(entitySave.isRated()) {
					Entity entity = entitySave.getEntity();
					entity.setEvaluationSum(- entitySave.getEvaluation());
					entity.setEvaluationSum(+ entitySaveDTO.getEvaluation());
					entity.setEvaluationAverage();
					entityService.save(entity);
				}else {
					Entity entity = entitySave.getEntity();
					entity.setEvaluationSum(+ entitySaveDTO.getEvaluation());
					entity.setEvaluationQuantity(+ 1);
					entity.setEvaluationAverage();
					entityService.save(entity);
				}
				break;
			case SEASON:
				if(entitySave.isRated()) {
					Season season = entitySave.getSeason();
					season.setEvaluationSum(- entitySave.getEvaluation());
					season.setEvaluationSum(+ entitySaveDTO.getEvaluation());
					season.setEvaluationAverage();
					seasonService.save(season);
				}else {
					Season season = entitySave.getSeason();
					season.setEvaluationSum(+ entitySaveDTO.getEvaluation());
					season.setEvaluationQuantity(+ 1);
					season.setEvaluationAverage();
					seasonService.save(season);
				}
				break;
			case EPISODE:
				if(entitySave.isRated()) {
					Episode episode = entitySave.getEpisode();
					episode.setEvaluationSum(- entitySave.getEvaluation());
					episode.setEvaluationSum(+ entitySaveDTO.getEvaluation());
					episode.setEvaluationAverage();
					episodeService.save(episode);
				}else {
					Episode episode = entitySave.getEpisode();
					episode.setEvaluationSum(+ entitySaveDTO.getEvaluation());
					episode.setEvaluationQuantity(+ 1);
					episode.setEvaluationAverage();
					episodeService.save(episode);
				}
				break;
			}
			
			entitySave.setEvaluation(entitySaveDTO.getEvaluation());
			entitySave.setRated(true);
			entitySave = entitySaveRepository.save(entitySave);
			EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
			return ResponseEntity.accepted().body(entitySaveMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> updateEntitySaveGoal(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
			entitySave.setGoal(entitySaveDTO.isGoal());		
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
//	public ResponseEntity<Object> updateEntitySaveRated(EntitySaveDTO entitySaveDTO){
//		try {
//			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
//			entitySave.setRated(entitySaveDTO.isRated());		
//			entitySave = entitySaveRepository.save(entitySave);
//			return ResponseEntity.accepted().build();
//		}catch (RuntimeException e) {
//			return ResponseEntity.badRequest().build();
//		}
//	}
	
	public ResponseEntity<Object> updateEntitySaveReview(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
			entitySave.setReviewed(entitySaveDTO.isReviewed());		
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//delete
	
	public ResponseEntity<Object> deleteEntitySaveEntity(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getIdUser()).getBody();
			Entity entity = (Entity) entityService.findById(entitySaveDTO.getIdEntity()).getBody();
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
			user.getEntitySaves().remove(entitySave);
			userService.save(user);
			entity.getEntitySaves().remove(entitySave);
			entityService.save(entity);
			entitySaveRepository.delete(entitySave);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> deleteEntitySaveSeason(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getIdUser()).getBody();
			Season season = (Season) seasonService.findById(entitySaveDTO.getIdSeason()).getBody();
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
			user.getEntitySaves().remove(entitySave);
			userService.save(user);
			season.getEntitySaves().remove(entitySave);
			seasonService.save(season);
			entitySaveRepository.delete(entitySave);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> deleteEntitySaveEpisode(EntitySaveDTO entitySaveDTO){
		try {
			User user = (User) userService.findById(entitySaveDTO.getIdUser()).getBody();
			Episode episode = (Episode) entityService.findById(entitySaveDTO.getIdEpisode()).getBody();
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getIdEntitySave()).get();
			user.getEntitySaves().remove(entitySave);
			userService.save(user);
			episode.getEntitySaves().remove(entitySave);
			episodeService.save(episode);
			entitySaveRepository.delete(entitySave);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//internal
	
	public ResponseEntity<Object> save(EntitySave entitySave){
		try {
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.accepted().body(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
