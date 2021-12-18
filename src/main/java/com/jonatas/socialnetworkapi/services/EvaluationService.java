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
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.EvaluationDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EvaluationMiniDTO;
import com.jonatas.socialnetworkapi.repositories.EvaluationRepository;

@Service
public class EvaluationService {

	//repositories
	
	@Autowired
	private EvaluationRepository evaluationRepository;
		
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
	private EntitySaveService entitySaveService;
	
	
	//methods
	
	public ResponseEntity<Object> findAllMini() {
		try {
			List<Evaluation> list = evaluationRepository.findAll();
			List<EvaluationMiniDTO> evaluationMiniDTOs = new ArrayList<>();
			for(Evaluation evaluation : list) {
				EvaluationMiniDTO evaluationMiniDTO = new EvaluationMiniDTO(evaluation);
				evaluationMiniDTOs.add(evaluationMiniDTO);
			}
			return ResponseEntity.ok().body(evaluationMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Evaluation evaluation = evaluationRepository.findById(id).get();
			EvaluationMiniDTO evaluationMiniDTO = new EvaluationMiniDTO(evaluation);
			return ResponseEntity.ok().body(evaluationMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
	public ResponseEntity<Object> newEvaluationEntity(EvaluationDTO evaluationDTO){
		try {
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			Entity entity = (Entity) entityService.findById(evaluationDTO.getIdEntity()).getBody();
			
			if(user == null) {
				return ResponseEntity.badRequest().build();
			}
			
			List<EntitySave> entitySaves = user.getEntitySaves();
			
			if(entitySaves.size() == 0) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			for(EntitySave entitySave : entitySaves) {
				boolean entitySaveExists = false;
				if(entitySave.getEntity().getId().hashCode() == entity.getId().hashCode()) {
					entitySaveExists = true;
					if(entitySave.isRated()) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}
				if(!entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			Evaluation evaluation = new Evaluation(user, entity, null, null, evaluationDTO.getValue(), evaluationDTO.getRelease(), evaluationDTO.getTypeEntity());
			evaluation = evaluationRepository.insert(evaluation);
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.getEntity().getId().hashCode() == entity.getId().hashCode()) {
					entitySave.setEvaluation(evaluation);
					entitySave.setRated(true);
					entitySaveService.save(entitySave);
				}
			}
			entity.setEvaluationQuantity(1);
			entity.setEvaluationSum(evaluation.getValue());
			entity.setEvaluationAverage();
			entityService.save(entity);
			EvaluationMiniDTO evaluationMiniDTO = new EvaluationMiniDTO(evaluation);
			return ResponseEntity.created(null).body(evaluationMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> newEvaluationSeason(EvaluationDTO evaluationDTO){
		try {
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			Season season = (Season) seasonService.findById(evaluationDTO.getIdSeason()).getBody();
			if(user == null) {
				return ResponseEntity.badRequest().build();
			}
			List<EntitySave> entitySaves = user.getEntitySaves();
			if(entitySaves.size() == 0) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			for(EntitySave entitySave : entitySaves) {
				boolean entitySaveExists = false;
				if(entitySave.getSeason().getId().hashCode() == season.getId().hashCode()) {
					entitySaveExists = true;
					if(entitySave.isRated()) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}
				if(!entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			Evaluation evaluation = new Evaluation(user, null, season, null, evaluationDTO.getValue(), evaluationDTO.getRelease(), evaluationDTO.getTypeEntity());
			evaluation = evaluationRepository.insert(evaluation);
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.getSeason().getId().hashCode() == season.getId().hashCode()) {
					entitySave.setEvaluation(evaluation);
					entitySave.setRated(true);
					entitySaveService.save(entitySave);
				}
			}
			season.setEvaluationQuantity(1);
			season.setEvaluationSum(evaluation.getValue());
			season.setEvaluationAverage();
			seasonService.save(season);
			EvaluationMiniDTO evaluationMiniDTO = new EvaluationMiniDTO(evaluation);
			return ResponseEntity.created(null).body(evaluationMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> newEvaluationEpisode(EvaluationDTO evaluationDTO){
		try {
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			Episode episode = (Episode) episodeService.findById(evaluationDTO.getIdEpisode()).getBody();
			if(user == null) {
				return ResponseEntity.badRequest().build();
			}
			List<EntitySave> entitySaves = user.getEntitySaves();
			if(entitySaves.size() == 0) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			for(EntitySave entitySave : entitySaves) {
				boolean entitySaveExists = false;
				if(entitySave.getEpisode().getId().hashCode() == episode.getId().hashCode()) {
					entitySaveExists = true;
					if(entitySave.isRated()) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}
				if(!entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			Evaluation evaluation = new Evaluation(user, null, null, episode, evaluationDTO.getValue(), evaluationDTO.getRelease(), evaluationDTO.getTypeEntity());
			evaluation = evaluationRepository.insert(evaluation);
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.getEpisode().getId().hashCode() == episode.getId().hashCode()) {
					entitySave.setEvaluation(evaluation);
					entitySave.setRated(true);
					entitySaveService.save(entitySave);
				}
			}
			episode.setEvaluationQuantity(1);
			episode.setEvaluationSum(evaluation.getValue());
			episode.setEvaluationAverage();
			episodeService.save(episode);
			EvaluationMiniDTO evaluationMiniDTO = new EvaluationMiniDTO(evaluation);
			return ResponseEntity.created(null).body(evaluationMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//put
	
	public ResponseEntity<Void> updateEvaluationEntity(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getIdEvaluation()).get();
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			Entity entity = evaluation.getEntity();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			double current = evaluationDTO.getValue();
			if(current > 5.0 || current < 1) {
				return ResponseEntity.badRequest().build();
			}
			List<EntitySave> entitySaves = user.getEntitySaves();
			for(EntitySave entitySave : entitySaves) {
				boolean entitySaveExists = false;
				if(entitySave.getEntity().getId().hashCode() == entity.getId().hashCode()) {
					entitySaveExists = true;
					if(!entitySave.isRated()) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}
				if(!entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			double previus = evaluation.getValue();
			evaluation.setValue(current);
			evaluationRepository.save(evaluation);
			entity.setEvaluationSum(- previus);
			entity.setEvaluationSum(current);
			entity.setEvaluationAverage();
			entityService.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updateEvaluationSeason(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getIdEvaluation()).get();
			Season season = evaluation.getSeason();
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			double current = evaluationDTO.getValue();
			if(current > 5.0 || current < 1) {
				return ResponseEntity.badRequest().build();
			}
			List<EntitySave> entitySaves = user.getEntitySaves();
			for(EntitySave entitySave : entitySaves) {
				boolean entitySaveExists = false;
				if(entitySave.getSeason().getId().hashCode() == season.getId().hashCode()) {
					entitySaveExists = true;
					if(!entitySave.isRated()) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}
				if(!entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			double previus = evaluation.getValue();
			evaluation.setValue(current);
			evaluationRepository.save(evaluation);	
			season.setEvaluationSum(- previus);
			season.setEvaluationSum(current);
			season.setEvaluationAverage();
			seasonService.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updateEvaluationEpisode(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getIdEvaluation()).get();
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			Episode episode = evaluation.getEpisode();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			double current = evaluationDTO.getValue();
			if(current > 5.0 || current < 1) {
				return ResponseEntity.badRequest().build();
			}
			List<EntitySave> entitySaves = user.getEntitySaves();
			for(EntitySave entitySave : entitySaves) {
				boolean entitySaveExists = false;
				if(entitySave.getEpisode().getId().hashCode() == episode.getId().hashCode()) {
					entitySaveExists = true;
					if(!entitySave.isRated()) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}
				if(!entitySaveExists) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			double previus = evaluation.getValue();
			evaluation.setValue(current);
			evaluationRepository.save(evaluation);
			episode.setEvaluationSum(- previus);
			episode.setEvaluationSum(current);
			episode.setEvaluationAverage();
			episodeService.save(episode);	
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//delete
	
	public ResponseEntity<Void> deleteEvaluationEntity(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getIdEvaluation()).get();
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			double previus = evaluation.getValue();
			Entity entity = evaluation.getEntity();
				entity.setEvaluationSum(- previus);
				entity.setEvaluationQuantity(- 1);
				entity.setEvaluationAverage();
				entityService.save(entity);
			List<EntitySave> entitySaves = user.getEntitySaves();	
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.getEntity().getId().hashCode() == entity.getId().hashCode()) {
					entitySave.setEvaluation(null);
					entitySave.setRated(false);
					entitySaveService.save(entitySave);
				}
			}
			evaluationRepository.delete(evaluation);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> deleteEvaluationSeason(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getIdEvaluation()).get();
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			double previus = evaluation.getValue();
			Season season = evaluation.getSeason();
			season.setEvaluationSum(- previus);
			season.setEvaluationQuantity(- 1);
			season.setEvaluationAverage();
			seasonService.save(season);
			List<EntitySave> entitySaves = user.getEntitySaves();	
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.getSeason().getId().hashCode() == season.getId().hashCode()) {
					entitySave.setEvaluation(null);
					entitySave.setRated(false);
					entitySaveService.save(entitySave);
				}
			}
			evaluationRepository.delete(evaluation);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> deleteEvaluationEpisode(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getIdEvaluation()).get();
			User user = (User) userService.findById(evaluationDTO.getIdUser()).getBody();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			double previus = evaluation.getValue();
			Episode episode = evaluation.getEpisode();
			episode.setEvaluationSum(- previus);
			episode.setEvaluationQuantity(- 1);
			episode.setEvaluationAverage();
			episodeService.save(episode);	
			List<EntitySave> entitySaves = user.getEntitySaves();	
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.getEpisode().getId().hashCode() == episode.getId().hashCode()) {
					entitySave.setEvaluation(null);
					entitySave.setRated(false);
					entitySaveService.save(entitySave);
				}
			}
			evaluationRepository.delete(evaluation);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//service
	
	public ResponseEntity<Object> findById(String id){
		try {
			Evaluation evaluation = evaluationRepository.findById(id).get();
			return ResponseEntity.ok().body(evaluation);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
