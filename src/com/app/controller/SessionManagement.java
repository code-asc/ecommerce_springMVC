package com.app.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import com.app.repository.UserDetails;

public class SessionManagement implements HttpSessionListener
{

	
	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
		
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{ 
		UserDetails info = new UserDetails();
		info.removeUserStatus((int)event.getSession().getAttribute("userID"));
	}
	

}
