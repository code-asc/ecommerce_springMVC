package com.app.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.service.UserLogin;

@Component
public class AuthenticationSuccessConfigure implements AuthenticationSuccessHandler{

	@Autowired
	UserLogin userDetails;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		 
		
		User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userEmail = authUser.getUsername();
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(1000*60);
		userDetails.getLoggedInUserDetails(userEmail, session);
		String redirectPath = null;
		if(session.getAttribute("previousPath") == null)
		{
			redirectPath = session.getAttribute("tempPath").toString();
		}else
		{
			redirectPath = session.getAttribute("previousPath").toString();
			
		}
		response.sendRedirect(redirectPath);
	}

}
