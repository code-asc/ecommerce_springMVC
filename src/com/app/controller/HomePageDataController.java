package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.HomePageLargeImage;
import com.app.model.HomePageThumbNail;
import com.app.service.HomePageDisplay;


@Controller
public class HomePageDataController {

	@Autowired
	HomePageDisplay pageDisplay;
	
	@RequestMapping(value="/getHomePageLargeContent" , method=RequestMethod.GET)
	public @ResponseBody List<HomePageLargeImage> pageData()
	{
		return pageDisplay.homePageDisplay();
	}
	
	@RequestMapping(value="/getHomePageThumbNailContent" , method=RequestMethod.GET)
	public @ResponseBody List<HomePageThumbNail> pageDataThumNail()
	{
		return pageDisplay.homePageDisplayThumbNail();
	}
}
