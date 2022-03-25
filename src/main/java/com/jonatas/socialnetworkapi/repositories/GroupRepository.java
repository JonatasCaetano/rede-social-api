package com.jonatas.socialnetworkapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jonatas.socialnetworkapi.entities.Group;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

}
