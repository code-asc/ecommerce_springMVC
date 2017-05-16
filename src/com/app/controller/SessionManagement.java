package com.app.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionManagement implements HttpSessionListener
{
	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
		System.out.println("new session created");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{ 
		
		System.out.println("session destroyed");
		
	}
	

}
