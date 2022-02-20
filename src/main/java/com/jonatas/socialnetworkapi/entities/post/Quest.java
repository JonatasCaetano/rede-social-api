package com.jonatas.socialnetworkapi.entities.post;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.helper.VoteQuest;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class Quest extends Post {
	private static final long serialVersionUID = 1L;
	
	private List<String> options = new ArrayList<>();
	private int votesQuantity = 0;
	private List<VoteQuest> usersVotes = new ArrayList<>();
	private List<Integer> votes = new ArrayList<>();
	
	public Quest() {
		super();
	}

	public Quest(String release, String body, TypePost typePost, TypePostVisibility typePostVisibility, User author,
			Boolean spoiler, List<String> options, int votesQuantity, List<Integer> votes) {
		super(release, body, typePost, typePostVisibility, author, spoiler);
		this.options = options;
		this.votesQuantity = votesQuantity;
		this.votes = votes;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public int getVotesQuantity() {
		return votesQuantity;
	}

	public void setVotesQuantity(int votesQuantity) {
		this.votesQuantity += votesQuantity;
	}

	public List<VoteQuest> getUsersVotes() {
		return usersVotes;
	}

	public List<Integer> getVotes() {
		return votes;
	}

	public void setVotes(List<Integer> votes) {
		this.votes = votes;
	}

	

}
