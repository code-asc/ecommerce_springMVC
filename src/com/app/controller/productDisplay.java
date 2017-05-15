package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class productDisplay {
	
	@RequestMapping(value="/user_action" , method=RequestMethod.GET)
	public String getProductDisplayPage(Model model)
	{
		return "user_action";
	}
}
