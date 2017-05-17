package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.model.SignInModel;
import com.app.service.DoUserLogin;


@SessionAttributes("name")
@Controller
public class SignIn {

	@Autowired
	DoUserLogin doUserLogin;
	
	
	@RequestMapping(value="/signin" , method=RequestMethod.GET)
	public String onGetForSignIn(@ModelAttribute("loginForm") SignInModel signInModel, HttpServletRequest request , HttpSession session , Model model)
	{
		//System.out.println("the user part : "+request.getSession(false));

		if(session.getAttribute("isUserLoggedIn")==null)
		{
		return "signIn";
		}
		else
		{
			return "redirect:index.html";
		}
	}
	
	
	@RequestMapping(value="/signin" , method=RequestMethod.POST)
	public String onPostForSignIn(@ModelAttribute("loginForm") SignInModel signInModel , Model model , HttpSession session , HttpServletRequest request)
	{
		if(doUserLogin.isUserValid(signInModel.getEmail() , signInModel.getPassword() , session , request))
		{
			return "redirect:index.html";
		}else
		{
			model.addAttribute("showMessage", true);
			model.addAttribute("signInStatus","Invaild Credentials");
			return "signIn";
		}
	}
	
	


}
