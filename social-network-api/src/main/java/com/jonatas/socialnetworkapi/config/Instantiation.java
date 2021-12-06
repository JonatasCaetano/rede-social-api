package com.jonatas.socialnetworkapi.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.controllers.EvaluationController;
import com.jonatas.socialnetworkapi.dto.EpisodeDTO;
import com.jonatas.socialnetworkapi.dto.EvaluationDTO;
import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Evaluation;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
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
	private EvaluationRepository evaluationRepository;
	
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
	private EvaluationService evaluationService;
	
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

		User user1 = new User("marley alexandre", "marley@gmail.com","123456", null, "um cachorro legal", null, "Bauru");//123456
		User user2 = new User("bela caetano", "bela@gmail.com","654351", null, "viciada em bola", null, "Iacanga");//654351
		User user3 = new User("mel alexandre", "mel@gmail.com","681236", null, "a menina da vovó", null, "Macatuba");//681236
		
		userRepository.insert(user1);
		userRepository.insert(user2);
		userRepository.insert(user3);
		
		user1.setChecked(true);
		userRepository.save(user1);
		
		Follower follower1 = followerRepository.insert(new Follower(null, user1));
		user1.setFollower(follower1);
		userRepository.save(user1);
		Follower follower2 = followerRepository.insert(new Follower(null, user2));
		user2.setFollower(follower2);
		userRepository.save(user2);
		Follower follower3 = followerRepository.insert(new Follower(null, user3));
		user3.setFollower(follower3);
		userRepository.save(user3);
		
		invitationService.createdInvitation(user1);
		invitationService.createdInvitation(user2);
		invitationService.createdInvitation(user3);
		
		Entity entity1 = new Entity("Vingadores", "Loki (Tom Hiddleston) retorna à Terra enviado pelos chitauri, uma raça alienígena que pretende dominar os humanos.", null, "2012", 0);
		Entity entity2 = new Entity("O Senhor dos Anéis - A Sociedade do Anel", "Numa terra fantástica e única, chamada Terra-Média, um hobbit (seres de estatura entre 80 cm e 1,20 m, com pés peludos e bochechas um pouco avermelhadas) recebe de presente de seu tio o Um Anel, um anel mágico e maligno que precisa ser destruído antes que caia nas mãos do mal.", null, "2001", 0 );
		Entity entity3 = new Entity("American Horror Story", "A série gira em torno dos Harmon, uma família de três que se desloca de Boston para Los Angeles a fim de resolver alguns problemas do passado.", null, "2011", 1);
		Entity entity4 = new Entity("The Walking Dead", "Baseado na história em quadrinhos escrita por Robert Kirkman, este drama potente e visceral retrata a vida nos Estados Unidos pós-apocalíptico.", null, "2010", 1);
		
		entityRepository.saveAll(Arrays.asList(entity1, entity2, entity3, entity4));
		
		WorkerDTO worker1 = new WorkerDTO(user1.getId(), entity2.getId(), "ator");
		WorkerDTO worker2 = new WorkerDTO(user2.getId(), entity2.getId(), "atriz");
		WorkerDTO worker3 = new WorkerDTO(user1.getId(), entity1.getId(), "Diretor");
		
		workerService.create(worker1);
		workerService.create(worker2);
		workerService.create(worker3);
		
		followerService.addFollowing(user1.getId(), user3.getId());
		followerService.addFollowing(user2.getId(), user1.getId());
		followerService.addFollowing(user3.getId(), user2.getId());
		
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
		
		EvaluationDTO evaluation1 = new EvaluationDTO(user2.getId(), entity4.getId(), season3.getId(), ep1.getId(), 3.0, null);
		EvaluationDTO evaluation2 = new EvaluationDTO(user2.getId(), entity4.getId(), season3.getId(), ep2.getId(), 4.0, null);
		EvaluationDTO evaluation3 = new EvaluationDTO(user2.getId(), entity4.getId(), null, null, 2.5, null);
		EvaluationDTO evaluation4 = new EvaluationDTO(user2.getId(), entity4.getId(), season3.getId(), null, 3.5, null);
		EvaluationDTO evaluation5 = new EvaluationDTO(user1.getId(), entity4.getId(), season3.getId(), ep2.getId(), 1.0, null);
		
		evaluationService.newEvaluation(evaluation1);
		evaluationService.newEvaluation(evaluation2);
		evaluationService.newEvaluation(evaluation3);
		evaluationService.newEvaluation(evaluation4);
		evaluationService.newEvaluation(evaluation5);
	}

	
	
	
	
	
	
	
	
	
	

}
