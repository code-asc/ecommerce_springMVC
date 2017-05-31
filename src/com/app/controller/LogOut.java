package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.service.AdminPageAllInfo;


@Controller
public class LogOut {
	
	@Autowired
	AdminPageAllInfo info;
	
	@RequestMapping(value="/logout" ,  method=RequestMethod.GET)
	public String logout(HttpSession session)
	{
		info.removeUserOnline((int)session.getAttribute("userID"));
		session.invalidate();
		return "redirect:index.html";
	}
}
