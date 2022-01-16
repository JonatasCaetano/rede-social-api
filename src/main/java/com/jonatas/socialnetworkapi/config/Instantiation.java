package com.jonatas.socialnetworkapi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.EntityDTO;
import com.jonatas.socialnetworkapi.enuns.TypeEntity;
import com.jonatas.socialnetworkapi.repositories.EditionRepository;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
import com.jonatas.socialnetworkapi.repositories.EntitySaveRepository;
import com.jonatas.socialnetworkapi.repositories.EpisodeRepository;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;
import com.jonatas.socialnetworkapi.repositories.InvitationRepository;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;
import com.jonatas.socialnetworkapi.repositories.WorkerRepository;
import com.jonatas.socialnetworkapi.services.EditionService;
import com.jonatas.socialnetworkapi.services.EntitySaveService;
import com.jonatas.socialnetworkapi.services.EntityService;
import com.jonatas.socialnetworkapi.services.EpisodeService;
import com.jonatas.socialnetworkapi.services.FollowerService;
import com.jonatas.socialnetworkapi.services.InvitationService;
import com.jonatas.socialnetworkapi.services.SeasonService;
import com.jonatas.socialnetworkapi.services.UserService;
import com.jonatas.socialnetworkapi.services.WorkerService;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	
	//Repositories

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EntityRepository entityRepository;
	@Autowired
	private WorkerRepository workerRepository;
	@Autowired
	private FollowerRepository followerRepository;
	@Autowired
	private InvitationRepository invitationRepository;
	@Autowired
	private SeasonRepository seasonRepository;
	@Autowired
	private EpisodeRepository episodeRepository;
	@Autowired
	private EditionRepository editionRepository;
	@Autowired
	private EntitySaveRepository entitySaveRepository;
	
	//Services
	
	@Autowired
	private UserService userService;
	@Autowired
	private EntityService entityService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private FollowerService followerService;
	@Autowired
	private InvitationService invitationService;
	@Autowired
	private SeasonService seasonService;
	@Autowired
	private EpisodeService episodeService;
	@Autowired
	private EditionService editionService;
	@Autowired
	private EntitySaveService entitySaveService;
	
	//start of function 
	
	@Override
	public void run(String... args) throws Exception {
		
//		userRepository.deleteAll();	
//		workerRepository.deleteAll();
//		followerRepository.deleteAll();
//		invitationRepository.deleteAll();
//		entityRepository.deleteAll();
//		seasonRepository.deleteAll();
//		episodeRepository.deleteAll();
//		editionRepository.deleteAll();
//		entitySaveRepository.deleteAll();
//		User user1 = new User("marley alexandre", "marley@gmail.com","123456", "um cachorro legal", "Bauru");//123456
//		userRepository.insert(user1);	
//		invitationService.createdInvitation(user1);
//		userRepository.save(user1);
//		user1.setChecked(true);
//		userRepository.save(user1);
//		Follower follower1 = followerRepository.insert(new Follower(null, user1));
//		user1.setFollower(follower1);
//		userRepository.save(user1);	
//		
//		EntityDTO entityDTO1 = new EntityDTO(
//				"Game of Thrones",
//				"Game of Thrones é uma série de televisão norte-americana criada por David Benioff e D. B. Weiss, baseada na série de livros A Song of Ice and Fire de George R. R. Martin. Eleita como a melhor série de TV do século XXI em 2020, numa votação popular feita pela revista Digital Spy, Game of Thrones foi transmitida originalmente pelo canal HBO entre 17 de abril de 2011 a 19 de maio de 2019",
//				TypeEntity.SERIES
//				);
//				
//				
//		
//		EntityDTO entityDTO2 = new EntityDTO(
//				"American Horror Story", 
//				"American Horror Story (às vezes, abreviado como AHS) é uma série de televisão estadunidense antológica de terror criada e produzida por Ryan Murphy e Brad Falchuk, onde cada temporada narra uma história independente (com próprio começo, meio e fim), seguindo um conjunto de personagens e ambientações distintas.", 
//				TypeEntity.SERIES
//				);
//		
//		EntityDTO entityDTO3 = new EntityDTO(
//				"Homem-Aranha", 
//				"Peter Parker (Tobey Maguire) em uma excursão visita um laboratório de genética a exposição de aranhas de 15 espécies junto com seu amigo Harry Osborn (James Franco) e seu interesse amoroso, Mary Jane Watson (Kirsten Dunst). Lá, Peter é picado por uma aranha geneticamente modificada.",
//				TypeEntity.MOVIES
//				);
//				
//		entityService.createEntity(entityDTO1, user1.getId());
//		entityService.createEntity(entityDTO2, user1.getId());
//		entityService.createEntity(entityDTO3, user1.getId());
	}	
}
