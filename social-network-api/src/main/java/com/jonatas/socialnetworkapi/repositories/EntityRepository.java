package com.jonatas.socialnetworkapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jonatas.socialnetworkapi.entities.Entity;

public interface EntityRepository extends MongoRepository<Entity, String>{

}
