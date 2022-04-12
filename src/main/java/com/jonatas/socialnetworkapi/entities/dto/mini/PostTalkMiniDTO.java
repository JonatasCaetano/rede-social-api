package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.post.Talk;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class PostTalkMiniDTO {

	private String id;
	private String release;
	private String body;
	private TypePost typePost;
	private TypePostVisibility typePostVisibility;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	private TypeObject typeObject = TypeObject.POST;
	private Boolean spoiler;
	private UserMicroWidgetDTO author;
	private boolean liked;
	private UserMicroWidgetDTO like;
	private String title;
	
	public PostTalkMiniDTO() {
		super();
	}

	public PostTalkMiniDTO(Talk post, User user) {
		super();
		this.id = post.getId();
		this.release = post.getRelease();
		this.body = post.getBody();
		this.typePost = post.getTypePost();
		this.typePostVisibility = post.getTypePostVisibility();
		this.likeQuantity = post.getLikeQuantity();
		this.commentQuantity = post.getCommentQuantity();
		this.typeObject = post.getTypeObject();
		this.spoiler = post.getSpoiler();
		this.author = post.getAuthor() != null ? new UserMicroWidgetDTO(post.getAuthor()) : null;
		this.title = post.getTitle();
		setLike(post, user);
		setLiked(post, user);
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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
	
	public UserMicroWidgetDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserMicroWidgetDTO author) {
		this.author = author;
	}

	public void setLike(Talk post, User user) {
		if(!post.getLikes().isEmpty()) {
			UserMicroWidgetDTO userMicroWidgetDTO = new UserMicroWidgetDTO(post.getLikes().get(0));
			if(userMicroWidgetDTO.getId().hashCode() != user.getId().hashCode()) {
				this.like = userMicroWidgetDTO;
			}else {
				if(post.getLikes().size() > 1) {
					userMicroWidgetDTO = new UserMicroWidgetDTO(post.getLikes().get(1));
					this.like = userMicroWidgetDTO;
				}
			}
		}
	}
	
	public UserMicroWidgetDTO getLike() {
		return like;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(Talk post, User user) {
		if(post.getLikes().contains(user)) {
			this.liked = true;
		}else {
			this.liked = false;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
