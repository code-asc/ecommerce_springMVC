package com.app.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class DelegateFilterProxy extends AbstractSecurityWebApplicationInitializer {
	
	public DelegateFilterProxy()
	{
		super(SecurityConfig.class);
	}
}
