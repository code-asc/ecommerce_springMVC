package com.app.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * The DelegateFilterProxy class extends AbstractWebApplicationInitializer
 * It is used to load SecurityConfig class for each request.
 */
public class DelegateFilterProxy extends AbstractSecurityWebApplicationInitializer {
	
	public DelegateFilterProxy()
	{
		super(SecurityConfig.class);
	}
}
