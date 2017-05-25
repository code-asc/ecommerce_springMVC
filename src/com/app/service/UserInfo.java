package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.LoggedInUserInfo;
import com.app.repository.UserDetails;

@Service
public class UserInfo {

	@Autowired
	UserDetails details;
	


	public List<LoggedInUserInfo> getUserDetails(int userID)
	{
		return details.getUserInfo(userID);
	}
}
