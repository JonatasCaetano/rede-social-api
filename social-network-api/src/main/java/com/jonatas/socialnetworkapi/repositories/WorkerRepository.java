package com.jonatas.socialnetworkapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jonatas.socialnetworkapi.entities.Worker;

@Repository
public interface WorkerRepository extends MongoRepository<Worker, String>{

}
