package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.SignUpModel;
import com.app.service.RegisterUser;


@Controller
/**
 * The SignUp class allows the user register new account
 */
public class SignUp {
	
	@Autowired
	RegisterUser registerNewUser;

	/**
	 * onGetForSignUp method redirects the user to signUp page.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param signUpModel of type SignUpModel.
	 * @param session of type HttpSession
	 * @param request of type HttpRequest
	 */
	@RequestMapping(value="/signup" , method=RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String onGetForSignUp(@ModelAttribute("signUpDetails") SignUpModel signUpModel , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn")==null)
		{
		return "signUp";
		}
		else
		{
			return "redirect:index.html";
		}
	}
	
	
	/**
	 * onPostForSignUp method get the input fields from signUp page.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param signUpModel of type SignUpModel.
	 * @param session of type HttpSession
	 * @param request of type HttpRequest
	 */
	@RequestMapping(value="/signup" , method=RequestMethod.POST , produces = MediaType.TEXT_HTML_VALUE)
	public String onPostForSignUp(@Valid @ModelAttribute("signUpDetails") SignUpModel signUpModel , BindingResult result , Model model)
	{
		if(!result.hasErrors())
		{
		String firstName=signUpModel.getFirstName();
		String middleName=signUpModel.getMiddleName();
		String lastName=signUpModel.getLastName();
		String email=signUpModel.getEmail();
		String password=signUpModel.getPassword();
		String mobile=signUpModel.getMobile();
		if(registerNewUser.doUserRegister(firstName ,  middleName ,  lastName ,  email ,  password , mobile))
		{
			model.addAttribute("showMessage",true);
			model.addAttribute("signUpStatus", "Registered Successful . Please Sign In .");
			return "signUp";
			
		}
		else
		{
			signUpModel.setEmail("");
			model.addAttribute("status",true);
			model.addAttribute("signUpStatus", "Already Registered With this Email");
			return "signUp";
		
		}
		}
		else
		{
			return "signUp";
	}
	}
	
}
