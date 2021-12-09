package com.jonatas.socialnetworkapi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.dto.EpisodeDTO;
import com.jonatas.socialnetworkapi.dto.EvaluationDTO;
import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.dto.UserCreationDTO;
import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.dto.mini.EntityMiniDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.EditionRepository;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
import com.jonatas.socialnetworkapi.repositories.EntitySaveRepository;
import com.jonatas.socialnetworkapi.repositories.EpisodeRepository;
import com.jonatas.socialnetworkapi.repositories.EvaluationRepository;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;
import com.jonatas.socialnetworkapi.repositories.InvitationRepository;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;
import com.jonatas.socialnetworkapi.repositories.WorkerRepository;
import com.jonatas.socialnetworkapi.services.EntityService;
import com.jonatas.socialnetworkapi.services.EpisodeService;
import com.jonatas.socialnetworkapi.services.EvaluationService;
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
	private EvaluationRepository evaluationRepository;
	
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
	
//	@Autowired
//	private FollowerService followerService;
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SeasonService seasonService;
	
	@Autowired
	private EpisodeService episodeService;
	
	@Autowired
	private EvaluationService evaluationService;
	
//	@Autowired
//	private EditionService editionService;
	
//	@Autowired
//	private EntitySaveService entitySaveService;
	
	//start of function 
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		entityRepository.deleteAll();
		workerRepository.deleteAll();
		followerRepository.deleteAll();
		invitationRepository.deleteAll();
		seasonRepository.deleteAll();
		episodeRepository.deleteAll();
		evaluationRepository.deleteAll();
		editionRepository.deleteAll();
		entitySaveRepository.deleteAll();

		User user1 = new User("marley alexandre", "marley@gmail.com","123456", null, "um cachorro legal", null, "Bauru");//123456

		userRepository.insert(user1);	
		invitationService.createdInvitation(user1);
		userRepository.save(user1);
		user1.setChecked(true);
		userRepository.save(user1);
		Follower follower1 = followerRepository.insert(new Follower(null, user1));
		user1.setFollower(follower1);
		userRepository.save(user1);
		
		UserCreationDTO userCreation2 = new UserCreationDTO("Bela Caetano", "bela@gmail.com", user1.getInvitation().getValue(), "123456");
		UserCreationDTO userCreation3 = new UserCreationDTO("Mel Alexandre", "mel@gmail.com", user1.getInvitation().getValue(), "123456");
		
		User user2 = (User) userService.createUser(userCreation2).getBody();
		User user3 = (User) userService.createUser(userCreation3).getBody();
				
		Entity entity1 = new Entity("Vingadores", null, "Loki (Tom Hiddleston) retorna à Terra enviado pelos chitauri, uma raça alienígena que pretende dominar os humanos.", null, 0, null);
		Entity entity2 = new Entity("O Senhor dos Anéis - A Sociedade do Anel", null, "Numa terra fantástica e única, chamada Terra-Média, um hobbit (seres de estatura entre 80 cm e 1,20 m, com pés peludos e bochechas um pouco avermelhadas) recebe de presente de seu tio o Um Anel, um anel mágico e maligno que precisa ser destruído antes que caia nas mãos do mal.", null, 0, null );
		Entity entity3 = new Entity("American Horror Story", null, "A série gira em torno dos Harmon, uma família de três que se desloca de Boston para Los Angeles a fim de resolver alguns problemas do passado.", null, 1, null);
		Entity entity4 = new Entity("The Walking Dead", null, "Baseado na história em quadrinhos escrita por Robert Kirkman, este drama potente e visceral retrata a vida nos Estados Unidos pós-apocalíptico.", null, 1, null);
				
		entity1 = (Entity) entityService.createEntity(new EntityMiniDTO(entity1), user1.getId()).getBody();
		entity2 = (Entity) entityService.createEntity(new EntityMiniDTO(entity2), user1.getId()).getBody();
		entity3 = (Entity) entityService.createEntity(new EntityMiniDTO(entity3), user1.getId()).getBody();
		entity4 = (Entity) entityService.createEntity(new EntityMiniDTO(entity4), user1.getId()).getBody();
		
		WorkerDTO worker1 = new WorkerDTO(user1.getId(), entity2.getId(), "ator");
		WorkerDTO worker2 = new WorkerDTO(user2.getId(), entity2.getId(), "atriz");
		WorkerDTO worker3 = new WorkerDTO(user3.getId(), entity1.getId(), "Diretor");
		
		workerService.newWorker(worker1);
		workerService.newWorker(worker2);
		workerService.newWorker(worker3);
		
		SeasonDTO seasonDTO1 = new SeasonDTO("Murder House", "A primeira temporada, intitulada Murder House, tem como tema principal a infidelidade. Explorando temas como o amor, a família, e o perdão.", null, null, 1);
		SeasonDTO seasonDTO2 = new SeasonDTO("Asylum", "A segunda temporada, intitulada Asylum, tem como tema a sanidade. A história se passa em 1964 e acompanha os pacientes, médicos e freiras que ocupam a Instituição Mental Briarcliff, fundada para tratar e abrigar os criminosos insanos.", null, null, 2);
		SeasonDTO seasonDTO3 = new SeasonDTO("1ª temporada", "Rick Grimes é o xerife de uma pequena cidade do estado da Georgia, quando certo dia, é baleado por criminosos durante uma perseguição e entra em coma. Semanas depois, ele acorda em um hospital abandonado e totalmente danificado.", null, null, 1);
		
		Season season1 = (Season) seasonService.newSeason(seasonDTO1, user1.getId(), entity3.getId()).getBody();
		Season season2 = (Season) seasonService.newSeason(seasonDTO2, user1.getId(), entity3.getId()).getBody();
		Season season3 = (Season) seasonService.newSeason(seasonDTO3, user1.getId(), entity4.getId()).getBody();
		
		EpisodeDTO episodeDTO1 = new EpisodeDTO("Pilot", "Em 1978, dois gêmeos ultrapassam o portão da Casa dos Assassinatos. Adelaide os avisa que, se entrarem na casa, irão morrer. Eles desobedecem e entram mesmo assim.",  null, null, 1);
		EpisodeDTO episodeDTO2 = new EpisodeDTO("Home Invasion", "Em 1968, Maria é enganada ao ajudar um rapaz que finge estar ferido e acaba sendo assassinada a facadas por ele. Em 2011, Tate provoca Ben durante sua consulta ao lembrar de sua infidelidade à Vivien.", null, null, 2);

		Episode ep1 = (Episode) episodeService.newEpisode(episodeDTO1, user1.getId(), season1.getId()).getBody();
		Episode ep2 = (Episode) episodeService.newEpisode(episodeDTO2, user1.getId(), season1.getId()).getBody();
		
		EvaluationDTO evaluation1 = new EvaluationDTO(user2.getId(), null, null, ep1.getId(), 3.0, null, 2);
		EvaluationDTO evaluation2 = new EvaluationDTO(user2.getId(), null, null, ep2.getId(), 4.0, null, 2);
		EvaluationDTO evaluation3 = new EvaluationDTO(user1.getId(), entity4.getId(), null, null, 2.5, null, 0);
		EvaluationDTO evaluation4 = new EvaluationDTO(user1.getId(), null, season2.getId(), null, 3.5, null, 1);
		EvaluationDTO evaluation5 = new EvaluationDTO(user1.getId(), null, season3.getId(), null, 1.0, null, 1);
		EvaluationDTO evaluation6 = new EvaluationDTO(user1.getId(), null, null, ep1.getId(), 1.0, null, 2);
		
		evaluationService.newEvaluation(evaluation1);
		evaluationService.newEvaluation(evaluation2);
		evaluationService.newEvaluation(evaluation3);
		evaluationService.newEvaluation(evaluation4);
		evaluationService.newEvaluation(evaluation5);
		evaluationService.newEvaluation(evaluation6);
	}

	
	
	
}
