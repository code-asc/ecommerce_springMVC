package com.app.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class EditUserProfile {

	private String userFirstName;
	private String userMiddleName;
	private String userLastName;
	private String userEmail;
	private CommonsMultipartFile userProfilePhoto;
	private String userPhone;
	

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
	public CommonsMultipartFile getUserProfilePhoto() {
		return userProfilePhoto;
	}
	public void setUserProfilePhoto(CommonsMultipartFile userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
}
