package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EntitySaveDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Episode;
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
					false,
					false,
					false,
					0
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
					false,
					false,
					false,
					1
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
					false,
					false,
					false,
					2
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
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> updateEntitySaveCategory(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			if(entitySaveDTO.getCategory() < 1 || entitySaveDTO.getCategory() > 5) {
				return ResponseEntity.badRequest().build();
			}
			entitySave.setCategory(entitySaveDTO.getCategory());
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> updateEntitySaveGoal(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			entitySave.setGoal(entitySaveDTO.isGoal());		
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> updateEntitySaveRated(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			entitySave.setRated(entitySaveDTO.isRated());		
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.ok(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> updateEntitySaveReview(EntitySaveDTO entitySaveDTO){
		try {
			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
			entitySave.setReview(entitySaveDTO.isReview());		
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
	
	public ResponseEntity<Object> save(EntitySave entitySave){
		try {
			entitySave = entitySaveRepository.save(entitySave);
			return ResponseEntity.accepted().body(entitySave);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
