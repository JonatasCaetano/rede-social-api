package com.jonatas.socialnetworkapi.entities.helper;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.jonatas.socialnetworkapi.entities.Post;

public class PostUser {

	@DBRef(lazy = true)
	private Post post;

	public PostUser() {
		super();
	}

	public PostUser(Post post) {
		super();
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		return Objects.hash(post);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostUser other = (PostUser) obj;
		return Objects.equals(post, other.post);
	}
	
	
}
