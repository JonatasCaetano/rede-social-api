package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
}
