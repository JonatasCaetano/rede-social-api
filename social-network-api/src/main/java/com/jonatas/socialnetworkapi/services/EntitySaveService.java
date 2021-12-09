package com.jonatas.socialnetworkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
			Season season = (Season) seasonService.findById(entitySaveDTO.getEntity()).getBody();
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
			Episode episode = (Episode) episodeService.findById(entitySaveDTO.getEntity()).getBody();
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
	
	public ResponseEntity<Object> updateEntitySaveEntity(EntitySaveDTO entitySaveDTO){
		try {
]			EntitySave entitySave = entitySaveRepository.findById(entitySaveDTO.getId()).get();
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
	
}
