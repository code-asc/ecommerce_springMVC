package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The index class redirect the user to index on the request.
 */
@Controller
public class Index {

	/**
	 *  onIndexGet method provides redirect the user to index on the request..
	 * @param model of type Model. It is used to modify the view accordingly.
	 */
	@RequestMapping(value="index" , method=RequestMethod.GET)
	public String onIndexGet(Model model)
	{
		return "index";
	}
}
