package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.jonatas.socialnetworkapi.dto.EvaluationEntityDTO;
import com.jonatas.socialnetworkapi.dto.SeasonEntityDTO;
import com.jonatas.socialnetworkapi.dto.WorkerEntityDTO;
import com.jonatas.socialnetworkapi.dto.mini.EntityMiniDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;

@Service
public class EntityService {

	//repositories
	
	@Autowired
	private EntityRepository entityRepository;
	
	//services
	
	@Autowired
	private UserService userService;
		
	//methods
	
	public ResponseEntity<Object> findAll(){
		List<Entity> list = entityRepository.findAll();
		List<EntityMiniDTO> entityMiniDTOs = new ArrayList<>();
		for(Entity entity : list) {
			EntityMiniDTO entityMiniDTO = new EntityMiniDTO(entity);
			entityMiniDTOs.add(entityMiniDTO);
		}
		return ResponseEntity.ok().body(entityMiniDTOs);
	}
	
	public ResponseEntity<Object> findById(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			return ResponseEntity.ok().body(entity);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getWorkers(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			List<Worker> workers = entity.getWorkers();
			List<WorkerEntityDTO> workerEntityDTOs = new ArrayList<>();
			for(Worker worker : workers) {
				WorkerEntityDTO workerEntityDTO = new WorkerEntityDTO(worker);
				workerEntityDTOs.add(workerEntityDTO);
			}
			return ResponseEntity.ok().body(workerEntityDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findAllSeasons(@PathVariable String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			List<Season> seasons = entity.getSeasons();
			List<SeasonEntityDTO> seasonEntityDTOs = new ArrayList<>();
			for(Season season : seasons) {
				SeasonEntityDTO seasonEntityDTO = new SeasonEntityDTO(season);
				seasonEntityDTOs.add(seasonEntityDTO);
			}
			return ResponseEntity.ok().body(seasonEntityDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	public ResponseEntity<Object> createEntity(Entity entity, String id){
		try {
			User user = (User) userService.findById(id).getBody();
			if(user.isChecked()) {
				try {
					Entity obj = entityRepository.insert(entity);
					return ResponseEntity.created(null).body(obj);
				}catch(RuntimeException e) {
					return ResponseEntity.badRequest().build();
				}
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
	public ResponseEntity<Object> save(Entity entity){
		try {
			Entity obj = entityRepository.save(entity);
			return ResponseEntity.ok().body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> getEvaluationsEntity(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			List<Evaluation> evaluations = entity.getEvaluations();
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
}
