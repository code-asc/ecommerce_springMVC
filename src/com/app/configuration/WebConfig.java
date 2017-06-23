package com.app.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.app.controller.SessionManagement;
import com.app.filter.FilterToCheckSessionExists;


public class WebConfig implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		container.addListener( new SessionManagement());
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.app.configuration.DispatcherServletConfig");
		ServletRegistration.Dynamic dispatcherServlet = container.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcherServlet.addMapping("/");
		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.setAsyncSupported(true);
		
		//SessionManagementListener
		container.addListener("com.app.controller.SessionManagement");
		
		//Filters
		FilterRegistration.Dynamic redirectFilter = container.addFilter("redirectFilter", new FilterToCheckSessionExists());
		redirectFilter.addMappingForUrlPatterns(null, true, "*.html");
		redirectFilter.setInitParameter("encoding", "UTF-8");
		redirectFilter.setInitParameter("forceEncoding", "true");
		
	}

}
