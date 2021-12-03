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
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class SeasonService {

	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityRepository entityRepository;
	
	public ResponseEntity<List<SeasonDTO>> findAll(){
		List<Season> seasons = seasonRepository.findAll();
		List<SeasonDTO> seasonDTOs = new ArrayList<>();
		for(Season season : seasons) {
			SeasonDTO seasonDTO = new SeasonDTO(season);
			seasonDTOs.add(seasonDTO);
		}
		return ResponseEntity.ok().body(seasonDTOs);
	}
	
	public ResponseEntity<Season> newSeason(SeasonDTO seasonDTO, String id){
		try {
			User user = userRepository.findById(id).get();
			Entity entity = entityRepository.findById(seasonDTO.getEntity()).get();
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
						entityRepository.save(entity);
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
}
