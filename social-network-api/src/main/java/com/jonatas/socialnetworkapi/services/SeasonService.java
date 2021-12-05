package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;

@Service
public class SeasonService {

	//repositories
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	//services
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityService entityService;
	
	//methods
	
	public ResponseEntity<List<SeasonDTO>> findAll(){
		List<Season> seasons = seasonRepository.findAll();
		List<SeasonDTO> seasonDTOs = new ArrayList<>();
		for(Season season : seasons) {
			SeasonDTO seasonDTO = new SeasonDTO(season);
			seasonDTOs.add(seasonDTO);
		}
		return ResponseEntity.ok().body(seasonDTOs);
	}
	
	public ResponseEntity<Season> findById(String id){
		try {
			Season season = seasonRepository.findById(id).get();
			return ResponseEntity.ok().body(season);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Season> save(Season season){
		try {
			Season obj = seasonRepository.save(season);
			return ResponseEntity.accepted().body(obj);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Season> newSeason(SeasonDTO seasonDTO, String idUser, String idEntity){
		try {
			User user = userService.findById(idUser).getBody();
			Entity entity = entityService.findById(idEntity).getBody();
			Season season = new Season(seasonDTO);
			List<Season> seasons = entity.getSeasons();
			if(user.isChecked()) {
				if(seasons.contains(season)) {
					return ResponseEntity.badRequest().build();
				}
					try {
						season.setEntity(entity);
						Season obj = seasonRepository.insert(season);
						entity.getSeasons().add(obj);
						entity.setSeason(entity.getSeason() + 1);
						entityService.save(entity);
						return ResponseEntity.created(null).body(obj);
					}catch(RuntimeException e) {
						return ResponseEntity.badRequest().build();
					}
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
