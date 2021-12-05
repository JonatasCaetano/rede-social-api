package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.EvaluationRepository;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;
}
