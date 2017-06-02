package com.app.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		System.out.println((int)event.getSession().getAttribute("userID"));
		info.removeUserStatus((int)event.getSession().getAttribute("userID"));
	}
	

}
