package com.jonatas.socialnetworkapi.entities.dto.mini;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jonatas.socialnetworkapi.entities.Invitation;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.enuns.TypeObject;

public class InvitationMiniDTO {

    private String id;
	private Date release;	
	private String value;
	private UserMicroWidgetDTO user;
	private TypeObject typeObject = TypeObject.INVITATION;
	private List<UserMicroWidgetDTO>  invited = new ArrayList<>();
	
	public InvitationMiniDTO() {
		super();
	}
	
	public InvitationMiniDTO(Invitation invitation) {
		super();
		this.id = invitation.getId();
		this.release = invitation.getRelease();
		this.value = invitation.getValue();
		this.user = invitation.getUser() != null ? new UserMicroWidgetDTO(invitation.getUser()) : null;
		setInvited(invitation.getInvited());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UserMicroWidgetDTO getUser() {
		return user;
	}

	public void setUser(UserMicroWidgetDTO user) {
		this.user = user;
	}
	
	public List<UserMicroWidgetDTO> getInvited() {
		return invited;
	}

	public void setInvited(List<User> invited) {
		for(User user : invited) {
			if(user != null) {
				UserMicroWidgetDTO userMicroWidgetDTO = new UserMicroWidgetDTO(user);
				this.invited.add(userMicroWidgetDTO);
			}
		}
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}
}
