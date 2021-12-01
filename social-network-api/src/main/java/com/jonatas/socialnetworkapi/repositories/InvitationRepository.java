package com.jonatas.socialnetworkapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jonatas.socialnetworkapi.entities.Invitation;

public interface InvitationRepository extends MongoRepository<Invitation, String>{

}
