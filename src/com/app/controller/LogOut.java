package com.app.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LogOut {

	@RequestMapping(value="/logout" ,  method=RequestMethod.GET)
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:index.html";
	}
}
