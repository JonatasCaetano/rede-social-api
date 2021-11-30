package com.jonatas.socialnetworkapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();

		User user1 = new User(null, "marley", "marley@gmail.com","123456");//123456
		User user2 = new User(null, "bela", "bela@gmail.com","654351");//654351
		User user3 = new User(null, "mel", "mel@gmail.com","681236");//681236
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));

	}
	
	
	
	
	
	
	
	
	
	
	
	

}
