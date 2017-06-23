package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.LoggedInUserInfo;
import com.app.repository.UserDetails;

@Service
public class DoUserLogin implements UserLogin {

	@Autowired
	UserDetails userDetails;

	/* (non-Javadoc)
	 * @see com.app.service.UserLogin#isUserValid(java.lang.String, java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean isUserValid(String email, String password , HttpSession session , HttpServletRequest request) {
		String userFirstName = "";
		String userLastName = "";
		String userMiddleName = "";
		String userFullName = "";
		String userEmail = "";
		String userProfilePhoto = "";
		String role = "";
		int userID = -1;
		int userCount = 0;
		boolean returnVal = false;
		
		
		try{
			List<LoggedInUserInfo> list = userDetails.doLogin(email, password);
			LoggedInUserInfo userInfo = list.get(0);
		    userCount = list.size();
			if(userCount == 1)
			{
				userFirstName = userInfo.getUserFirstName();
				userLastName = userInfo.getUserLastName();
				userMiddleName = userInfo.getUserMiddleName();
			    userEmail = userInfo.getUserEmail();
			    userProfilePhoto = userInfo.getUserProfilePhoto();
			    role = userInfo.getRole();
			    userID = userInfo.getUserID();
				session = request.getSession(true);
				userFullName = userFirstName+" "+ userMiddleName+" "+userLastName;
				session.setMaxInactiveInterval(360*60);
				session.setAttribute("userID", userID);
				session.setAttribute("userFullName", userFullName);
				session.setAttribute("isUserLoggedIn", true);
				session.setAttribute("userProfilePhoto" , userProfilePhoto);
				session.setAttribute("userEmail", userEmail);
				session.setAttribute("role", role);
				session.setAttribute("userInfo", userInfo);
				returnVal=true;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		return returnVal;
	
	}
	
	@Override
	public void getLoggedInUserDetails(String email , HttpSession session) 
	{		
		LoggedInUserInfo userInfo = userDetails.loggedInUserInfo(email);
		String userFirstName = "";
		String userLastName = "";
		String userMiddleName = "";
		String userFullName = "";
		String userEmail = "";
		String userProfilePhoto = "";
		String role = "";
		int userID = 0;
		if(userInfo != null)
		{
			userFirstName = userInfo.getUserFirstName();
			userLastName = userInfo.getUserLastName();
			userMiddleName = userInfo.getUserMiddleName();
		    userEmail = userInfo.getUserEmail();
		    userProfilePhoto = userInfo.getUserProfilePhoto();
		    role = userInfo.getRole();
		    userID = userInfo.getUserID();
			
			userFullName = userFirstName+" "+ userMiddleName+" "+userLastName;
			session.setMaxInactiveInterval(360*60);
			session.setAttribute("userID", userID);
			session.setAttribute("userFullName", userFullName);
			session.setAttribute("isUserLoggedIn", true);
			session.setAttribute("userProfilePhoto" , userProfilePhoto);
			session.setAttribute("userEmail", userEmail);
			session.setAttribute("role", role);
			session.setAttribute("userInfo", userInfo);
		}
	}
}
