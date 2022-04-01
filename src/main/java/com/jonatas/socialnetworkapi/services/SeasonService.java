package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Edition;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.EntitySave;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.EditionDTO;
import com.jonatas.socialnetworkapi.entities.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EditionMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EntitySaveMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.EpisodeMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.SeasonMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.enuns.Level;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;

@Service
public class SeasonService {

	//repositories
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private EntityService entityService;
	
	@Autowired
	@Lazy
	private EditionService editionService;
	
	//methods
	
	//get
	
	public ResponseEntity<Object> findAllMini(){
		try {
			List<Season> list = seasonRepository.findAll();
			List<SeasonMiniDTO> seasonMiniDTOs = new ArrayList<>();
			for(Season season : list) {
				SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
				seasonMiniDTOs.add(seasonMiniDTO);
			}
			return ResponseEntity.ok().body(seasonMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByName(String name){
		try {
			List<Season> list = seasonRepository.searchByName(name);
			List<SeasonMiniDTO> seasonMiniDTOs = new ArrayList<>();
			for(Season season : list) {
				SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
				seasonMiniDTOs.add(seasonMiniDTO);
			}
			return ResponseEntity.ok().body(seasonMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
			return ResponseEntity.ok().body(seasonMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	public ResponseEntity<Object> getAllEntitySaveMini(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			List<EntitySave> entitySaves = season.getEntitySaves();
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
		
	public ResponseEntity<Object> getEntitySaveMini(String idSeason, String idUser){
		try {
			Season season = seasonRepository.findById(idSeason).get();
			List<EntitySave> entitySaves = season.getEntitySaves();
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
	
	public ResponseEntity<Object> getEpisodesMini(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			List<Episode> episodes = season.getEpisodes();
			List<EpisodeMiniDTO> episodeMiniDTOs = new ArrayList<>();
			for(Episode episode : episodes) {
				EpisodeMiniDTO episodeMiniDTO = new EpisodeMiniDTO(episode);
				episodeMiniDTOs.add(episodeMiniDTO);
			}
			return ResponseEntity.ok().body(episodeMiniDTOs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> getEditionsMini(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			List<Edition> editions = season.getEditions();
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
	
	public ResponseEntity<Object> getReviewMini(String idSeason, String idUser){
		try {
			Season season = seasonRepository.findById(idSeason).get();
			User user = (User) userService.findById(idUser).getBody();
			List<EntitySave> entitySaves = season.getEntitySaves();
			List<EntitySaveMiniDTO> reviews = new ArrayList<>();
			for(EntitySave entitySave : entitySaves) {
				if(entitySave.isReviewed()) {
					EntitySaveMiniDTO entitySaveMiniDTO = new EntitySaveMiniDTO(entitySave);
					
					if(entitySave.getLikes().contains(user)) {
						entitySaveMiniDTO.setLiked(true);
					}else {
						entitySaveMiniDTO.setLiked(false);
					}
					if(!entitySave.getLikes().isEmpty()) {
						UserMiniDTO userMiniDTO = new UserMiniDTO(entitySave.getLikes().get(0));
						if(userMiniDTO.getId().hashCode() != idUser.hashCode()) {
							entitySaveMiniDTO.setLike(userMiniDTO);
						}else {
							if(entitySave.getLikes().size() > 1) {
								userMiniDTO = new UserMiniDTO(entitySave.getLikes().get(1));
								entitySaveMiniDTO.setLike(userMiniDTO);
							}
						}
					}
					reviews.add(entitySaveMiniDTO);
				}
			}
			return ResponseEntity.ok().body(reviews);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
	public ResponseEntity<Object> newSeason(SeasonDTO seasonDTO, String idUser, String idEntity){
		try {
			//User user = (User) userService.findById(idUser).getBody();
			Entity entity = (Entity) entityService.findById(idEntity).getBody();
			Season season = new Season(seasonDTO.getName(), seasonDTO.getDescription(), entity.getSeasonQuantity() + 1, entity);
			List<Season> seasons = entity.getSeasons();
			if(seasons.contains(season)) {
				return ResponseEntity.badRequest().build();
			}
				try {
					season.setEntity(entity);
					season = seasonRepository.insert(season);
					entity.getSeasons().add(season);
					entity.setSeasonQuantity(1);
					entityService.save(entity);
					SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
					return ResponseEntity.created(null).body(seasonMiniDTO);
				}catch(RuntimeException e) {
					return ResponseEntity.badRequest().build();
				}
//			if(user.isChecked()) {
//				//hashCode and equals
//				if(seasons.contains(season)) {
//					return ResponseEntity.badRequest().build();
//				}
//					try {
//						season.setEntity(entity);
//						season = seasonRepository.insert(season);
//						entity.getSeasons().add(season);
//						entity.setSeasonQuantity(1);
//						entityService.save(entity);
//						SeasonMiniDTO seasonMiniDTO = new SeasonMiniDTO(season);
//						return ResponseEntity.created(null).body(seasonMiniDTO);
//					}catch(RuntimeException e) {
//						return ResponseEntity.badRequest().build();
//					}
//			}else {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//			}
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	//put
	
	public ResponseEntity<Void> updateName(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
//			if(!user.isChecked()) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//			}
			Season season = seasonRepository.findById(editionDTO.getIdSeason()).get();
			editionDTO.setLevel(Level.SEASON);
			editionDTO.setAttribute("name");
			editionDTO.setPrevious(season.getName());
			season.setName((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getLevel());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> addImages(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
//			if(!user.isChecked()) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//			}
			Season season = seasonRepository.findById(editionDTO.getIdSeason()).get();		
			editionDTO.setLevel(Level.SEASON);
			editionDTO.setAttribute("image");
			editionDTO.setPrevious(season.getImage());
			season.setImage((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			editionDTO.setCurrent(season.getImage());
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getLevel());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Void> removeImages(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
//			if(!user.isChecked()) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//			}
			Season season = seasonRepository.findById(editionDTO.getIdSeason()).get();
			editionDTO.setLevel(Level.SEASON);
			editionDTO.setAttribute("image");
			editionDTO.setPrevious(season.getImage());
			season.setImage(null);
			seasonRepository.save(season);
			editionDTO.setCurrent(season.getImage());
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getLevel());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	public ResponseEntity<Void> updateDescription(EditionDTO editionDTO){
		try {
			User user = (User) userService.findById(editionDTO.getIdUser()).getBody();
//			if(!user.isChecked()) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//			}
			Season season = seasonRepository.findById(editionDTO.getIdSeason()).get();
			editionDTO.setAttribute("description");
			editionDTO.setPrevious(season.getDescription());
			season.setDescription((String) editionDTO.getCurrent());
			seasonRepository.save(season);
			Edition edition = new Edition(user, null, season, null, null, editionDTO.getPrevious(), editionDTO.getCurrent(), editionDTO.getAttribute(), editionDTO.getLevel());
			EditionMiniDTO editionMiniDTO = (EditionMiniDTO) editionService.newEdition(edition).getBody();
			edition = (Edition) editionService.findById(editionMiniDTO.getId()).getBody();
			season.getEditions().add(edition);
			seasonRepository.save(season);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
			
	
	//internal
	
	public ResponseEntity<Object> findById(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			return ResponseEntity.ok().body(season);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> save(Season season){
		try {
			Season obj = seasonRepository.save(season);
			return ResponseEntity.accepted().body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
}
