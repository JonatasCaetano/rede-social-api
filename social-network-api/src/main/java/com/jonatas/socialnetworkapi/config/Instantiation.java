package com.jonatas.socialnetworkapi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.entities.Follower;
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
			
	}
}
