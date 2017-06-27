package com.app.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.service.CartCount;
import com.app.service.UserLogin;


@SessionAttributes("name")
@Controller

/**
 * The SignIn class allows the user to authenticate and signIn
 */
public class SignIn {

	@Autowired
	UserLogin doUserLogin;
	
	@Autowired
	CartCount cartCount;

	final static Logger log = Logger.getLogger(SignIn.class);
	
	
	/**
	 *  springSecurityLogin method is used for login.
	 * @param principal of type Principal. It is used to check whether user is signed in with a username.
	 */
	
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public String springSecurityLogin(Principal principal)
	{
		log.info("Inside login method....");
		if(principal== null)
			{
				return "login";
			}else{
				return "redirect:index.html";
			}
	}
	

}
