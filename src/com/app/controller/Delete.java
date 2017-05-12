package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Delete {

	@RequestMapping(value="/delete.html")
	public String delete(Model model , HttpSession session , HttpServletRequest request)
	{
		session=request.getSession(true);
		session.setMaxInactiveInterval(5);
		//session.setMaxInactiveInterval(11);
		System.out.println(session.getAttribute("name")+"\n"+session.getMaxInactiveInterval());
		model.addAttribute("name","wow2....");
		return "delete";
	}
}
