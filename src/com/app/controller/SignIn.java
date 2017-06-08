package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.model.SignInModel;
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
	 *  onGetForSignIn method redirects the user to signIn page.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param signInModel of type SignInModel.
	 * @param session of type HttpSession
	 * @param request of type HttpRequest
	 */
	@RequestMapping(value = "/signin" , method = RequestMethod.GET)
	public String onGetForSignIn(@ModelAttribute("loginForm") SignInModel signInModel, HttpServletRequest request , HttpSession session , Model model)
	{
		//System.out.println("the user part : "+request.getSession(false));
		if(session.getAttribute("isUserLoggedIn") == null)
		{
		return "signIn";
		}
		else
		{
			return "redirect:index.html";
		}
	}
	
	
	/**
	 * onPostForSignIn method get the input fields from signIn page.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param signInModel of type SignInModel.
	 * @param session of type HttpSession
	 * @param request of type HttpRequest
	 */
	@RequestMapping(value = "/signin" , method = RequestMethod.POST)
	public String onPostForSignIn(@ModelAttribute("loginForm") SignInModel signInModel , Model model , HttpSession session , HttpServletRequest request)
	{
		log.info("Inside onPostForSignIn method....");
		if(doUserLogin.isUserValid(signInModel.getEmail() , signInModel.getPassword() , session , request))
		{
			try{
			int count = cartCount.getCartCount((int)session.getAttribute("userID"));
			session.setAttribute("cartCount" , count);		
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
				log.error("onPostForSignIn method : "+e);
			}
			//System.out.println("The current path : " + session.getAttribute("previousPath").toString());
			if(session.getAttribute("tempPath") != null)
			{
			return "redirect:"+session.getAttribute("tempPath").toString();
			}else
			{
				return "redirect:"+session.getAttribute("previousPath").toString();
			}
		}else
		{
			model.addAttribute("showMessage", true);
			model.addAttribute("signInStatus","Invaild Credentials");
			return "signIn";
		}
	}
	
	


}
