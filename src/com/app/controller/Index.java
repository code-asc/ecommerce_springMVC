package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Index {

	@RequestMapping(value="index" , method=RequestMethod.GET)
	public String onIndexGet(Model model)
	{
		return "index";
	}
}
