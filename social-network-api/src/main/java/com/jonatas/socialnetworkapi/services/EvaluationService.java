package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.EvaluationDTO;
import com.jonatas.socialnetworkapi.dto.EvaluationUserDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
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
	
	
	//methods
	
	public ResponseEntity<Object> findAll() {
		List<Evaluation> list = evaluationRepository.findAll();
		List<EvaluationUserDTO> evaluationUserDTOs = new ArrayList<>();
		for(Evaluation evaluation : list) {
			EvaluationUserDTO evaluationUserDTO = new EvaluationUserDTO(evaluation);
			evaluationUserDTOs.add(evaluationUserDTO);
		}
		return ResponseEntity.ok().body(evaluationUserDTOs);
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			Evaluation evaluation = evaluationRepository.findById(id).get();
			return ResponseEntity.ok().body(evaluation);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> newEvaluation(EvaluationDTO evaluationDTO){
		try {
			User user = (User) userService.findById(evaluationDTO.getUser()).getBody();
			Entity entity = (Entity) entityService.findById(evaluationDTO.getEntity()).getBody();
			Season season = (Season) seasonService.findById(evaluationDTO.getSeason()).getBody();
			Episode episode = (Episode) episodeService.findById(evaluationDTO.getEpisode()).getBody();
			Evaluation evaluation = new Evaluation(user, entity, season, episode, evaluationDTO.getValue(), evaluationDTO.getRelease(), evaluationDTO.getType());
			if(user == null) {
				return ResponseEntity.badRequest().build();
			}
			evaluation = evaluationRepository.insert(evaluation);
			user.getEvaluations().add(evaluation);
			userService.save(user);
			if(evaluation.getType() == 0) {
				entity.getEvaluations().add(evaluation);
				entity.setEvaluationQuantity(1);
				entity.setEvaluationSum(evaluation.getValue());
				entity.setEvaluationAverage();
				entityService.save(entity);
			}else if(evaluation.getType() == 1) {
				
					season.getEvaluations().add(evaluation);
					season.setEvaluationQuantity(1);
					season.setEvaluationSum(evaluation.getValue());
					season.setEvaluationAverage();
					seasonService.save(season);
		
			}else if(evaluation.getType() == 2){
				episode.getEvaluations().add(evaluation);
				episode.setEvaluationQuantity(1);
				episode.setEvaluationSum(evaluation.getValue());
				episode.setEvaluationAverage();
				episodeService.save(episode);	
			}else {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.created(null).body(evaluation);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> updateEvaluation(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getId()).get();
			User user = (User) userService.findById(evaluationDTO.getUser()).getBody();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			double current = evaluationDTO.getValue();
			if(current > 5.0) {
				return ResponseEntity.badRequest().build();
			}
			double previus = evaluation.getValue();
			evaluation.setValue(current);
			evaluationRepository.save(evaluation);
			Entity entity = evaluation.getEntity();
			Season season = evaluation.getSeason();
			Episode episode = evaluation.getEpisode();
			if(evaluation.getType() == 0) {
				entity.setEvaluationSum(- previus);
				entity.setEvaluationSum(current);
				entity.setEvaluationAverage();
				entityService.save(entity);
			}else if(evaluation.getType() == 1) {
				season.setEvaluationSum(- previus);
				season.setEvaluationSum(current);
				season.setEvaluationAverage();
				seasonService.save(season);
			}else if(evaluation.getType() == 2){
				episode.setEvaluationSum(- previus);
				episode.setEvaluationSum(current);
				episode.setEvaluationAverage();
				episodeService.save(episode);	
			}else {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> deleteEvaluation(EvaluationDTO evaluationDTO){
		try {
			Evaluation evaluation = evaluationRepository.findById(evaluationDTO.getId()).get();
			User user = (User) userService.findById(evaluationDTO.getUser()).getBody();
			if(user.getId().hashCode() != evaluation.getUser().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			user.getEvaluations().remove(evaluation);
			double previus = evaluation.getValue();
			Entity entity = evaluation.getEntity();
			Season season = evaluation.getSeason();
			Episode episode = evaluation.getEpisode();
			if(evaluation.getType() == 0) {
				entity.setEvaluationSum(- previus);
				entity.setEvaluationQuantity(- 1);
				entity.setEvaluationAverage();
				entity.getEvaluations().remove(evaluation);
				entityService.save(entity);
			}else if(evaluation.getType() == 1) {
					season.setEvaluationSum(- previus);
					season.setEvaluationQuantity(- 1);
					season.setEvaluationAverage();
					season.getEvaluations().remove(evaluation);
					seasonService.save(season);
			}else if(evaluation.getType() == 2){
				episode.setEvaluationSum(- previus);
				episode.setEvaluationQuantity(- 1);
				episode.setEvaluationAverage();
				episode.getEvaluations().remove(evaluation);
				episodeService.save(episode);	
			}else {
				return ResponseEntity.badRequest().build();
			}
			evaluationRepository.delete(evaluation);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
