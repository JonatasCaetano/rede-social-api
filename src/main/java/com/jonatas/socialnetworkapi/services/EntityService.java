package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.jonatas.socialnetworkapi.entities.Edition;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.entities.dto.EditionDTO;
import com.jonatas.socialnetworkapi.entities.dto.EntityDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EditionMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EntityMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EntitySaveMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.SeasonMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.WorkerMiniDTO;
import com.jonatas.socialnetworkapi.enuns.TypeEdition;
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
	
	//get
	
	public ResponseEntity<Object> findAllMini(){
		try {
			List<Entity> list = entityRepository.findAll();
			List<EntityMiniDTO> entityMiniDTOs = new ArrayList<>();
			for(Entity entity : list) {
				EntityMiniDTO entityMiniDTO = new EntityMiniDTO(entity);
				entityMiniDTOs.add(entityMiniDTO);
			}
			return ResponseEntity.ok().body(entityMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByName(String name){
		try {
			List<Entity> list = entityRepository.searchByName(name);
			List<EntityMiniDTO> entityMiniDTOs = new ArrayList<>();
			for(Entity entity : list) {
				EntityMiniDTO entityMiniDTO = new EntityMiniDTO(entity);
				entityMiniDTOs.add(entityMiniDTO);
			}
			return ResponseEntity.ok().body(entityMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			EntityMiniDTO entityMiniDTO = new EntityMiniDTO(entity);
			return ResponseEntity.ok().body(entityMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getWorkersMini(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			List<Worker> workers = entity.getWorkers();
			List<WorkerMiniDTO> workerMiniDTOs = new ArrayList<>();
			for(Worker worker : workers) {
				WorkerMiniDTO workerMiniDTO = new WorkerMiniDTO(worker);
				workerMiniDTOs.add(workerMiniDTO);
			}
			return ResponseEntity.ok().body(workerMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getSeasonsMini(@PathVariable String id){
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
	
	public ResponseEntity<Object> getAllEntitySaveMini(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			List<EntitySave> entitySaves = entity.getEntitySaves();
			List<EntitySaveMiniDTO> entitySaveMiniDTOs = new ArrayList<>();
			for(EntitySave entitySave : entitySaves) {
				EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
				entitySaveMiniDTOs.add(entitySaveMiniDTO);
			}
			return ResponseEntity.ok().body(entitySaveMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getEntitySaveMini(String idEntity, String idUser){
		try {
			Entity entity = entityRepository.findById(idEntity).get();
			List<EntitySave> entitySaves = entity.getEntitySaves();
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.getUser().getId().hashCode() == idUser.hashCode()) {
					EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
					return ResponseEntity.ok().body(entitySaveMiniDTO);
				}
			}	
			return ResponseEntity.notFound().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
		
	public ResponseEntity<Object> getEditionsMini(String id){
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
	
	//post
	
	public ResponseEntity<Object> createEntity(EntityDTO entityDTO, String id){
		try {
			User user = (User) userService.findById(id).getBody();
			Entity entity = new Entity(entityDTO);
			if(user.isChecked()) {
				try {
					Entity obj = entityRepository.insert(entity);
					EntityMiniDTO entityMiniDTO = new EntityMiniDTO(obj);
					return ResponseEntity.created(null).body(entityMiniDTO);
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
	
	//put
	
	public ResponseEntity<Void> updateName(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getIdEntity()).get();
			editionDTO.setTypeEdition(TypeEdition.ENTITY);
			editionDTO.setAttribute("name");
			editionDTO.setPrevious(entity.getName());
			entity.setName((String) editionDTO.getCurrent());
			entityRepository.save(entity);
			Edition edition = new Edition(user, entity, null, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> addImage(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getIdEntity()).get();
			
			editionDTO.setPrevious(entity.getImage());
			editionDTO.setTypeEdition(TypeEdition.ENTITY);
			editionDTO.setAttribute("image");
			entity.setImage((String) editionDTO.getCurrent());
			entityRepository.save(entity);
			editionDTO.setCurrent(entity.getImage());			
			Edition edition = new Edition(user, entity, null, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> removeImage(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
			if(!user.isChecked()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			Entity entity = entityRepository.findById(editionDTO.getIdEntity()).get();
			
			editionDTO.setPrevious(entity.getImage());
			editionDTO.setTypeEdition(TypeEdition.ENTITY);
			editionDTO.setAttribute("image");
			entity.setImage(null);
			entityRepository.save(entity);
			editionDTO.setCurrent(entity.getImage());
			Edition edition = new Edition(user, entity, null, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
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
			Entity entity = entityRepository.findById(editionDTO.getIdUser()).get();
			editionDTO.setTypeEdition(TypeEdition.ENTITY);
			editionDTO.setAttribute("description");
			editionDTO.setPrevious(entity.getDescription());
			entity.setDescription((String) editionDTO.getCurrent());
			entityRepository.save(entity);
			Edition edition = new Edition(user, entity, null, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getTypeEdition());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			entity.getEditions().add(edition);
			entityRepository.save(entity);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
				
	//internal
	
	public ResponseEntity<Object> findById(String id){
		try {
			Entity entity = entityRepository.findById(id).get();
			return ResponseEntity.ok().body(entity);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
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
}
