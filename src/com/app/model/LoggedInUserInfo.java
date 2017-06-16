package com.app.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.app.repository.UserDetails;


public class LoggedInUserInfo implements HttpSessionBindingListener {
	
	
	
	private static Map<String , HttpSession> logins = new ConcurrentHashMap<>();
	
	private int userID;
	private String userFirstName;
	private String userMiddleName;
	private String userLastName;
	private String userEmail;
	private String userProfilePhoto;
	private String role;
	private String userPhone;
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	public void setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		
		UserDetails info = new UserDetails();
		HttpSession session = logins.remove(this.userEmail);
		if(session != null)
		{
			info.removeUserStatus(this.userID);
			session.invalidate();
		}
		
		info.changeUserStatusOnline(this.userID, this.userEmail);
		logins.put(this.userEmail, event.getSession());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		
		logins.remove(this.userEmail);
	}

}
