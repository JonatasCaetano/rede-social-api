package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	private UserService userService;
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private SeasonService seasonService;
	
	@Autowired
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
			Evaluation evaluation = new Evaluation(user, entity, season, episode, evaluationDTO.getValue(), evaluationDTO.getRelease());
			if(user == null) {
				System.out.println("usuario nulo");
				return ResponseEntity.badRequest().build();
			}
			if(entity == null) {
				System.out.println("entidade nula");
				return ResponseEntity.badRequest().build();
			}
			Evaluation obj = evaluationRepository.insert(evaluation);
			user.getEvaluations().add(obj);
			userService.save(user);
			if(season == null && episode == null) {
				entity.getEvaluations().add(obj);
				entity.setEvaluationQuantity(1);
				entity.setEvaluationSum(evaluation.getValue());
				entity.setEvaluationAverage();
				entityService.save(entity);
			}else if(episode == null) {
				
					season.getEvaluations().add(obj);
					season.setEvaluationQuantity(1);
					season.setEvaluationSum(evaluation.getValue());
					season.setEvaluationAverage();
					seasonService.save(season);
		
			}else{
				episode.getEvaluations().add(obj);
				episode.setEvaluationQuantity(1);
				episode.setEvaluationSum(evaluation.getValue());
				episode.setEvaluationAverage();
				episodeService.save(episode);	
			}
			return ResponseEntity.created(null).body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
