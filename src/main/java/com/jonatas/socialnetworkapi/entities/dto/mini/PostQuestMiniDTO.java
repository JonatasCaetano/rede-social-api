package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.post.Quest;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class PostQuestMiniDTO {

	private String id;
	private String release;
	private TypePost typePost;
	private TypePostVisibility typePostVisibility;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	private TypeObject typeObject = TypeObject.POST;
	private Boolean spoiler;
	private UserMiniDTO author;
	private Boolean Liked;
	private Boolean voted;
	private int valueVoted;
	
	private String body;
	private List<String> options = new ArrayList<>();
	private int votesQuantity = 0;
	private List<Integer> votes = new ArrayList<>();
	
	public PostQuestMiniDTO() {
		super();
	}
	
	public PostQuestMiniDTO(String id, String release, TypePost typePost,
			TypePostVisibility typePostVisibility, int likeQuantity, int commentQuantity, TypeObject typeObject,
			Boolean spoiler, UserMiniDTO author, String body, List<String> options, int votesQuantity,
			List<Integer> votes) {
		super();
		this.id = id;
		this.release = release;
		this.typePost = typePost;
		this.typePostVisibility = typePostVisibility;
		this.likeQuantity = likeQuantity;
		this.commentQuantity = commentQuantity;
		this.typeObject = typeObject;
		this.spoiler = spoiler;
		this.author = author;
		this.body = body;
		this.options = options;
		this.votesQuantity = votesQuantity;
		this.votes = votes;
	}

	public PostQuestMiniDTO(Quest quest) {
		super();
		this.id = quest.getId();
		this.release = quest.getRelease();
		this.typePost = quest.getTypePost();
		this.typePostVisibility = quest.getTypePostVisibility();
		this.likeQuantity = quest.getLikeQuantity();
		this.commentQuantity = quest.getCommentQuantity();
		this.typeObject = quest.getTypeObject();
		this.spoiler = quest.getSpoiler();
		this.author = quest.getAuthor() != null ? new UserMiniDTO(quest.getAuthor()) : null;
		this.body = quest.getBody();
		this.options = quest.getOptions();
		this.votesQuantity = quest.getVotesQuantity();
		this.votes = quest.getVotes();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	public TypePost getTypePost() {
		return typePost;
	}

	public void setTypePost(TypePost typePost) {
		this.typePost = typePost;
	}

	public TypePostVisibility getTypePostVisibility() {
		return typePostVisibility;
	}

	public void setTypePostVisibility(TypePostVisibility typePostVisibility) {
		this.typePostVisibility = typePostVisibility;
	}

	public int getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(int likeQuantity) {
		this.likeQuantity = likeQuantity;
	}

	public int getCommentQuantity() {
		return commentQuantity;
	}

	public void setCommentQuantity(int commentQuantity) {
		this.commentQuantity = commentQuantity;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	public Boolean getSpoiler() {
		return spoiler;
	}

	public void setSpoiler(Boolean spoiler) {
		this.spoiler = spoiler;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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
		this.votesQuantity = votesQuantity;
	}

	public List<Integer> getVotes() {
		return votes;
	}

	public void setVotes(List<Integer> votes) {
		this.votes = votes;
	}

	public UserMiniDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserMiniDTO author) {
		this.author = author;
	}

	public Boolean getLiked() {
		return Liked;
	}

	public void setLiked(Boolean liked) {
		Liked = liked;
	}

	public Boolean getVoted() {
		return voted;
	}

	public void setVoted(Boolean voted) {
		this.voted = voted;
	}

	public int getValueVoted() {
		return valueVoted;
	}

	public void setValueVoted(int valueVoted) {
		this.valueVoted = valueVoted;
	}
	
		
}
