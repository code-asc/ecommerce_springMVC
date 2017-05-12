package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.SignUpModel;
import com.app.service.RegisterNewUser;


@Controller
public class SignUp {
	
	@Autowired
	RegisterNewUser registerNewUser;

	@RequestMapping(value="/signup" , method=RequestMethod.GET)
	public String onGetForSignUp(@ModelAttribute("signUpDetails") SignUpModel signUpModel)
	{
		return "signUp";
	}
	
	@RequestMapping(value="/signup" , method=RequestMethod.POST)
	public String onPostForSignUp(@ModelAttribute("signUpDetails") SignUpModel signUpModel , Model model)
	{
		ModelAndView mv=new ModelAndView();
		String firstName=signUpModel.getFirstName();
		String middleName=signUpModel.getMiddleName();
		String lastName=signUpModel.getLastName();
		String email=signUpModel.getEmail();
		String password=signUpModel.getPassword();
		String mobile=signUpModel.getMobile();
		if(registerNewUser.doUserRegister(firstName ,  middleName ,  lastName ,  email ,  password , mobile))
		{
			model.addAttribute("hideAndOnlyShowMessage",true);
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
	
}
