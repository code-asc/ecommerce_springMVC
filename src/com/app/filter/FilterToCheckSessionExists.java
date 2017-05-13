package com.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpServletResponse responseOf=(HttpServletResponse)response;
		
		System.out.println(requestOf.getRequestURI().toString());
		if(requestOf.getSession(false)==null && requestOf.getRequestURI().toString().compareTo("/ProjectDemo/signin.html")!=0)
		{
			System.out.println(requestOf.getRequestURI().toString());
			responseOf.sendRedirect("/ProjectDemo/signin.html");	
		}
		else
		{
			System.out.println("infintit chain....");
		chain.doFilter(request, response);
		}
			
			
			
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
