package com.jonatas.socialnetworkapi.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.InvitationRepository;
import com.jonatas.socialnetworkapi.repositories.UserRepository;

@Service
public class InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<List<Invitation>> findAll(){
		List<Invitation> list = invitationRepository.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	public ResponseEntity<Invitation> findByValue(String value){
		try {
			Invitation invitation = invitationRepository.findByValue(value);
			return ResponseEntity.ok().body(invitation);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	public Invitation createdInvitation(String id) {
		Random random = new Random();
		int numero = random.nextInt(24);
		int a = (numero - 4 < 0) ? 0 : numero - 4;
		int b = numero;
		System.out.println(a);
		System.out.println(b);
		try {
			User user = userRepository.findById(id).get();
			String nameUser = user.getName();
			String idUser = user.getId();
			String invitationFirst = nameUser.substring(0, 4);
			String invitationLast = idUser.substring(a, b);
			String invitationTotal = invitationFirst + invitationLast;
			System.out.println(invitationTotal);
				while(testInvitation(invitationTotal) == true) {
					System.out.println("entrou no while");
					numero = random.nextInt(24);
					a = (numero - 4 < 0) ? 0 : numero - 4;
					b = numero;
					System.out.println(a);
					System.out.println(b);
					invitationFirst = nameUser.substring(0, 4);
					invitationLast = idUser.substring(a, b);
					invitationTotal = invitationFirst + invitationLast;
					System.out.println(invitationTotal);
				}
				Invitation invitation = new Invitation(null, user, invitationTotal);
				Invitation result = invitationRepository.insert(invitation);
				user.setInvitation(result);
				userRepository.save(user);
				return result;

		}catch (RuntimeException e) {
			System.out.println("erro lançado: createdInvitation");
			throw new RuntimeException(e.getMessage());
		}
			
		}
	
		public boolean testInvitation(String invitationTotal) {
			try {
				Invitation invitation = invitationRepository.findByValue(invitationTotal);	
				invitation.getId();
				invitation.getUser();
				return true;
			}catch(RuntimeException e) {
				return false;
			}
		}
		
		
		public ResponseEntity<Void> addInvited(String userId, String invitationValue){
			try {
				User user = userRepository.findById(userId).get();
				Invitation invitation = invitationRepository.findByValue(invitationValue);
				invitation.getInvited().add(user);
				invitationRepository.save(invitation);
				return ResponseEntity.accepted().build();
			}catch(RuntimeException e) {
				System.out.println("erro lançado: addInvited");
				throw new RuntimeException(e.getMessage());
			}
			
		}
		
		public ResponseEntity<Void> checkAvailability(String invitationValue){
			try {
				Invitation invitation = invitationRepository.findByValue(invitationValue);
				List<User> list = invitation.getInvited();
				if(list.size() >= 5) {
					System.out.println("Lista invited contem: " + list.size());
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();			
				}else {
					return ResponseEntity.accepted().build();
				}
			}catch(RuntimeException e) {
				return ResponseEntity.notFound().build();
			}
	
			
		}
	
	
	}

