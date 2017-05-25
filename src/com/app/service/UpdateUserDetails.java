package com.app.service;

import javax.servlet.http.HttpSession;

public interface UpdateUserDetails {

	public abstract void setUserDetails(int userID, String firstName,
			String middleName, String lastName, String email, String phone,
			HttpSession session);

	public abstract void setUserProfilePhoto(int userID, String path,
			HttpSession session);

}