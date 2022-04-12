package com.jonatas.socialnetworkapi.entities.dto.mini;

import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.post.TalkGroup;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;

public class PostTalkGroupMiniDTO {
	
	private String id;
	private String release;
	private String body;
	private TypePost typePost;
	private TypePostVisibility typePostVisibility;
	private int likeQuantity = 0;
	private int commentQuantity = 0;
	private TypeObject typeObject = TypeObject.POST;
	private boolean spoiler;
	private UserMicroWidgetDTO author;
	private boolean liked;
	private UserMicroWidgetDTO like;
	private boolean close;
	private UserMicroWidgetDTO closedBy;
	private GroupMicroDTO group;
	private String title;
	
	public PostTalkGroupMiniDTO() {
		super();
	}
	
	public PostTalkGroupMiniDTO(TalkGroup post, User user) {
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
		this.close = post.isClose();
		this.closedBy = post.getClosedBy() != null ? new UserMicroWidgetDTO(post.getClosedBy()) : null;
		this.group = post.getGroup() != null ? new GroupMicroDTO(post.getGroup(), user) : null;
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

	public Boolean getLiked() {
		return liked;
	}

	public UserMicroWidgetDTO getAuthor() {
		return author;
	}

	public void setLiked(TalkGroup post, User user) {
		if(post.getLikes().contains(user)) {
			this.liked = true;
		}else {
			this.liked = false;
		}
	}

	public UserMicroWidgetDTO getLike() {
		return like;
	}

	public void setLike(TalkGroup post, User user) {
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

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}


	public UserMicroWidgetDTO getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(UserMicroWidgetDTO closedBy) {
		this.closedBy = closedBy;
	}

	public void setAuthor(UserMicroWidgetDTO author) {
		this.author = author;
	}

	public void setLike(UserMicroWidgetDTO like) {
		this.like = like;
	}

	public GroupMicroDTO getGroup() {
		return group;
	}

	public void setGroup(GroupMicroDTO group) {
		this.group = group;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
