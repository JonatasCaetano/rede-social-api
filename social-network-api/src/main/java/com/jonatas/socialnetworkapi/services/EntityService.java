package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.jonatas.socialnetworkapi.dto.EditionDTO;
import com.jonatas.socialnetworkapi.dto.EvaluationEntityDTO;
import com.jonatas.socialnetworkapi.dto.WorkerEntityDTO;
import com.jonatas.socialnetworkapi.dto.mini.EditionMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.EntityMiniDTO;
import com.jonatas.socialnetworkapi.dto.mini.SeasonMiniDTO;
import com.jonatas.socialnetworkapi.entities.Edition;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.EntitySave;
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
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private EditionService editionService;
		
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
			List<SeasonMiniDTO> seasonMiniDTOs = new ArrayList<>();
			for(Season season : seasons) {
				SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
				seasonMiniDTOs.add(seasonMiniDTO);
			}
			return ResponseEntity.ok().body(seasonMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	public ResponseEntity<Object> createEntity(EntityMiniDTO entityMiniDTO, String id){
		try {
			User user = (User) userService.findById(id).getBody();
			Entity entity = new Entity(entityMiniDTO);
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
			List<Evaluation> evaluations = new ArrayList<>();
			List<EntitySave> entitySaves = entity.getEntitySaves();
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.isRated()) {
					evaluations.add(entitySave.getEvaluation());
				}
			}
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
	
	public ResponseEntity<Object> getEditions(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			List<Edition> editions = entity.getEditions();
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
	
	//put
	
	public ResponseEntity<Void> updateName(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getEntity()).get();
			editionDTO.setAttribute("name");
			editionDTO.setPrevius(entity.getName());
			entity.setName((String) editionDTO.getCurrent());
			entityRepository.save(entity);
			Edition edition = new Edition(user, entity, null, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateImage(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getEntity()).get();
			editionDTO.setAttribute("image");
			editionDTO.setPrevius(entity.getImage());
			entity.setImage((String) editionDTO.getCurrent());
			entityRepository.save(entity);
			Edition edition = new Edition(user, entity, null, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateDescription(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getUser()).get();
			editionDTO.setAttribute("description");
			editionDTO.setPrevius(entity.getDescription());
			entity.setDescription((String) editionDTO.getCurrent());
			entityRepository.save(entity);
			Edition edition = new Edition(user, entity, null, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateRelease(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getEntity()).get();
			editionDTO.setAttribute("release");
			editionDTO.setPrevius(entity.getRelease());
			entity.setRelease((Date) editionDTO.getCurrent());
			entityRepository.save(entity);
			Edition edition = new Edition(user, entity, null, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> updateGenre(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getEntity()).get();
			editionDTO.setAttribute("genre");
			editionDTO.setPrevius(entity.getGenre());
			entity.setGenre((String) editionDTO.getCurrent());
			entityRepository.save(entity);
			Edition edition = new Edition(user, entity, null, null, editionDTO.getRelease(), editionDTO.getPrevius(), editionDTO.getCurrent(), editionDTO.getAttribute());
			edition = (Edition) editionService.newEdition(edition).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
}
