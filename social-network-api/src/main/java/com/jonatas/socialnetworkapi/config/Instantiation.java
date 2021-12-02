package com.jonatas.socialnetworkapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.dto.UserDTO;
import com.jonatas.socialnetworkapi.dto.WorkerDTO;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Follower;
import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.Worker;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
import com.jonatas.socialnetworkapi.repositories.FollowerRepository;
import com.jonatas.socialnetworkapi.repositories.InvitationRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;
import com.jonatas.socialnetworkapi.repositories.WorkerRepository;
import com.jonatas.socialnetworkapi.services.EntityService;
import com.jonatas.socialnetworkapi.services.FollowerService;
import com.jonatas.socialnetworkapi.services.InvitationService;
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
	
	//start of function 
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		entityRepository.deleteAll();
		workerRepository.deleteAll();
		followerRepository.deleteAll();
		invitationRepository.deleteAll();

		User user1 = new User(null, "marley", "marley@gmail.com","123456");//123456
		User user2 = new User(null, "bela", "bela@gmail.com","654351");//654351
		User user3 = new User(null, "melisa", "mel@gmail.com","681236");//681236
		
		userRepository.insert(user1);
		userRepository.insert(user2);
		userRepository.insert(user3);
		
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
		
		Entity entity1 = new Entity(null, "Vingadores", "Loki (Tom Hiddleston) retorna à Terra enviado pelos chitauri, uma raça alienígena que pretende dominar os humanos.");
		Entity entity2 = new Entity(null, "O Senhor dos Anéis - A Sociedade do Anel", "Numa terra fantástica e única, chamada Terra-Média, um hobbit (seres de estatura entre 80 cm e 1,20 m, com pés peludos e bochechas um pouco avermelhadas) recebe de presente de seu tio o Um Anel, um anel mágico e maligno que precisa ser destruído antes que caia nas mãos do mal." );
		
		entityRepository.saveAll(Arrays.asList(entity1, entity2));
		
		WorkerDTO worker1 = new WorkerDTO(user1.getId(), entity2.getId(), "ator");
		WorkerDTO worker2 = new WorkerDTO(user2.getId(), entity2.getId(), "atriz");
		WorkerDTO worker3 = new WorkerDTO(user1.getId(), entity1.getId(), "Diretor");
		
		workerService.create(worker1);
		workerService.create(worker2);
		workerService.create(worker3);
		
		followerService.addFollowing(user1.getId(), user3.getId());
		followerService.addFollowing(user2.getId(), user1.getId());
		followerService.addFollowing(user3.getId(), user2.getId());
		
		


	}

	
	
	
	
	
	
	
	
	
	

}
