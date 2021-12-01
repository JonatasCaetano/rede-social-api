package com.jonatas.socialnetworkapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jonatas.socialnetworkapi.entities.Follower;

@Repository
public interface FollowerRepository extends MongoRepository<Follower, String>{
	
	public Follower findByUser(String user);

}
