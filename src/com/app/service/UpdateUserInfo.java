package com.app.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.UserDetails;

@Service
public class UpdateUserInfo implements UpdateUserDetails {
	
	@Autowired
	UserDetails details;
	
	/* (non-Javadoc)
	 * @see com.app.service.UpdateUserDetails#setUserDetails(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Override
	public void setUserDetails(int userID , String firstName , String middleName , String lastName , String email , String phone , HttpSession session)
	{
		boolean check=details.updateUserProfileInfo(userID, firstName, middleName, lastName, email, phone);
		if(check)
		{
			String userFullName = firstName+" "+ middleName+" "+lastName;
			session.setAttribute("userFullName", userFullName);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.app.service.UpdateUserDetails#setUserProfilePhoto(int, java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Override
	public void setUserProfilePhoto(int userID , String path , HttpSession session)
	{
		boolean check=details.updateUserProfilePhoto(userID, path);
		if(check)
		{
			session.setAttribute("userProfilePhoto", path);
		}
	}
}
