package com.jonatas.socialnetworkapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.dto.SeasonDTO;
import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;
import com.jonatas.socialnetworkapi.repositories.InvitationRepository;
import com.jonatas.socialnetworkapi.repositories.SeasonRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;
import com.jonatas.socialnetworkapi.repositories.WorkerRepository;
import com.jonatas.socialnetworkapi.services.FollowerService;
import com.jonatas.socialnetworkapi.services.InvitationService;
import com.jonatas.socialnetworkapi.services.SeasonService;
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
	
	//Services
	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private EntityService entityService;
//	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private FollowerService followerService;
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SeasonService seasonService;
	
	//start of function 
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		entityRepository.deleteAll();
		workerRepository.deleteAll();
		followerRepository.deleteAll();
		invitationRepository.deleteAll();
		seasonRepository.deleteAll();

		User user1 = new User("marley", "marley@gmail.com","123456", null, "um cachorro legal");//123456
		User user2 = new User("bela", "bela@gmail.com","654351", null, "viciada em bola");//654351
		User user3 = new User("melisa", "mel@gmail.com","681236", null, "a menina da vovó");//681236
		
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
		
		invitationService.createdInvitation(user1.getId());
		invitationService.createdInvitation(user2.getId());
		invitationService.createdInvitation(user3.getId());
		
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
		
		SeasonDTO seasonDTO1 = new SeasonDTO("Murder House", null, "A primeira temporada, intitulada Murder House, tem como tema principal a infidelidade. Explorando temas como o amor, a família, e o perdão.", null, 1, entity3.getId());
		SeasonDTO seasonDTO2 = new SeasonDTO("Asylum", null, "A segunda temporada, intitulada Asylum, tem como tema a sanidade. A história se passa em 1964 e acompanha os pacientes, médicos e freiras que ocupam a Instituição Mental Briarcliff, fundada para tratar e abrigar os criminosos insanos.", null, 2, entity3.getId());
		SeasonDTO seasonDTO3 = new SeasonDTO("1ª temporada", null, "Rick Grimes é o xerife de uma pequena cidade do estado da Georgia, quando certo dia, é baleado por criminosos durante uma perseguição e entra em coma. Semanas depois, ele acorda em um hospital abandonado e totalmente danificado.", null, 1, entity4.getId());
		
		seasonService.newSeason(seasonDTO1, user1.getId());
		seasonService.newSeason(seasonDTO2, user1.getId());
		seasonService.newSeason(seasonDTO3, user1.getId());


	}

	
	
	
	
	
	
	
	
	
	

}
