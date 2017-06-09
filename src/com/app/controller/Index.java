package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.service.HomePageInfo;

/**
 * The index class redirect the user to index on the request.
 */
@Controller
public class Index {
	
	@Autowired
	HomePageInfo pageInfo;

	/**
	 *  onIndexGet method provides redirect the user to index on the request..
	 * @param model of type Model. It is used to modify the view accordingly.
	 */
	
	@RequestMapping(value="index" , method=RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String onIndexGet(Model model)
	{

		return "index";
	}
}
