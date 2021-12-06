package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.dto.InvitationDTO;
import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.repositories.InvitationRepository;

@Service
public class InvitationService {

	//repositories
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	@Lazy
	private FollowerService followerService;
		
	//methods
	
	public ResponseEntity<Object> findAll(){
		List<Invitation> list = invitationRepository.findAll();
		List<InvitationDTO> invitationDTOs = new ArrayList<>();
		for(Invitation invitation : list) {
			InvitationDTO invitationDTO = new InvitationDTO(invitation);
			invitationDTOs.add(invitationDTO);
		}
		return ResponseEntity.ok().body(invitationDTOs);
	}
	
	public ResponseEntity<Object> findByValue(String value){
		try {
			Invitation invitation = invitationRepository.findByValue(value);
			return ResponseEntity.ok().body(new InvitationDTO(invitation));
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	public ResponseEntity<Object> createdInvitation(User user) {
		String nameFinal;
		Random random = new Random();
		int numero = random.nextInt(24);
		int a = (numero - 4 < 0) ? 0 : numero - 4;
		int b = numero;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		try {
			String nameUser = user.getName();
			String idUser = user.getId();
			String[] name = nameUser.split(" ");
			int name1 = name[0].length();
			int name2 = name[1].length();
			System.out.println("name1: " + name1);
			System.out.println("name2: " + name2);
			if(name1 >= 4) {
				nameFinal = name[0].substring(0, 4);
			}else {
				nameFinal = name[1].substring(0, 4);
			}
			System.out.println(nameFinal);
			String invitationFirst = nameFinal.substring(0, 4);
			String invitationLast = idUser.substring(a, b);
			String invitationTotal = invitationFirst + invitationLast;
			System.out.println(invitationTotal);
				while(testInvitation(invitationTotal) == true) {
					numero = random.nextInt(24);
					a = (numero - 4 < 0) ? 0 : numero - 4;
					b = numero;
					System.out.println("a: " + a);
					System.out.println("b: " + b);
					invitationFirst = nameFinal.substring(0, 4);
					invitationLast = idUser.substring(a, b);
					invitationTotal = invitationFirst + invitationLast;
					System.out.println(invitationTotal);
				}
				Invitation invitation = new Invitation(null, user, invitationTotal);
				Invitation result = invitationRepository.insert(invitation);
				user.setInvitation(result);
				return ResponseEntity.created(null).body(user);

		}catch (RuntimeException e) {
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
		
		
		public ResponseEntity<Void> addInvited(User user, String invitationValue){
			try {
				Invitation invitation = invitationRepository.findByValue(invitationValue);
				invitation.getInvited().add(user);
				invitationRepository.save(invitation);			
				return ResponseEntity.accepted().build();
			}catch(RuntimeException e) {
				throw new RuntimeException(e.getMessage());
			}
			
		}
		
		public ResponseEntity<Boolean> checkAvailability(String invitationValue){
			try {
				Invitation invitation = invitationRepository.findByValue(invitationValue);
				List<User> list = invitation.getInvited();
				if(list.size() >= 5) {
					System.out.println("Lista invited contem: " + list.size());
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(false);			
				}else {
					return ResponseEntity.accepted().body(true);
				}
			}catch(RuntimeException e) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(false);	
			}
	
			
		}
		
		public ResponseEntity<Object> returnUser(String value){
			try {
				Invitation invitation = invitationRepository.findByValue(value);
				User user = invitation.getUser();
				return ResponseEntity.ok().body(user);
			}catch (RuntimeException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	
	
	}

