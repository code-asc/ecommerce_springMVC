package com.app.filter;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.app.repository.UserDetails;

/**
 * Servlet Filter implementation class FilterToCheckSessionExists
 */
@WebFilter("/FilterToCheckSessionExists")
public class FilterToCheckSessionExists implements Filter {

    /**
     * Default constructor. 
     */
    public FilterToCheckSessionExists() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest requestOf =(HttpServletRequest)request;
		/*The following 'if' condition creates a  thread for the request and makes LoggedIn user online*/
		 
		if(requestOf.getSession().getAttribute("isUserLoggedIn") != null && (boolean)requestOf.getSession().getAttribute("isUserLoggedIn"))
		{	
			AsyncContext async = requestOf.startAsync();
			//async.setTimeout(900000000);
			/*async.start(new Runnable(){ 
				public void run()
				{
					
					UserDetails info = new UserDetails();
					info.updateUserToOnline((int)requestOf.getSession().getAttribute("userID"));
					async.complete();
				}
			});*/
			async.start(() -> {
				
				UserDetails info = new UserDetails();
				info.updateUserToOnline((int)requestOf.getSession().getAttribute("userID"));
				async.complete();
			});
		}
		
		if(requestOf.getSession().getAttribute("previousPath") != null)
		{
			String tempPath = requestOf.getSession().getAttribute("previousPath").toString();
			requestOf.getSession().setAttribute("tempPath", tempPath);
		}
		
		if(requestOf.getSession().getAttribute("currentPath") != null)
		{
			String previousPath = requestOf.getSession().getAttribute("currentPath").toString();
			requestOf.getSession().setAttribute("previousPath", previousPath);
		}
		
		String currentPath = requestOf.getServletPath().replace("/", "");
		if(requestOf.getQueryString() != null)
		{
			currentPath = currentPath.concat("?" + requestOf.getQueryString());
		}
		
	
		requestOf.getSession().setAttribute("currentPath", currentPath);
		
			
		chain.doFilter(request, response);	
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
