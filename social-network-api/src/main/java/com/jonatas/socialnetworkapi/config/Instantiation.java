package com.jonatas.socialnetworkapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.EntityRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;
import com.jonatas.socialnetworkapi.repositories.WorkerRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Autowired
	private WorkerRepository workerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		entityRepository.deleteAll();
		workerRepository.deleteAll();

		User user1 = new User(null, "marley", "marley@gmail.com","123456");//123456
		User user2 = new User(null, "bela", "bela@gmail.com","654351");//654351
		User user3 = new User(null, "mel", "mel@gmail.com","681236");//681236
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		Entity entity1 = new Entity(null, "Vingadores", "Loki (Tom Hiddleston) retorna à Terra enviado pelos chitauri, uma raça alienígena que pretende dominar os humanos.");
		Entity entity2 = new Entity(null, "O Senhor dos Anéis - A Sociedade do Anel", "Numa terra fantástica e única, chamada Terra-Média, um hobbit (seres de estatura entre 80 cm e 1,20 m, com pés peludos e bochechas um pouco avermelhadas) recebe de presente de seu tio o Um Anel, um anel mágico e maligno que precisa ser destruído antes que caia nas mãos do mal." );
		
		entityRepository.saveAll(Arrays.asList(entity1, entity2));
		
		


	}

	
	
	
	
	
	
	
	
	
	

}
