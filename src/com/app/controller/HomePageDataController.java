package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.BrandsOnly;
import com.app.model.HeaderSubcategoryDetails;
import com.app.model.HomePageLargeImage;
import com.app.model.HomePageThumbNail;
import com.app.service.GetCategoryAndSubCategoryDetails;
import com.app.service.GetOnlyBrands;
import com.app.service.HomePageDisplay;


@Controller
public class HomePageDataController {

	@Autowired
	HomePageDisplay pageDisplay;
	
	@Autowired
	GetOnlyBrands onlyBrands;
	
	@Autowired
	GetCategoryAndSubCategoryDetails categoryAndSubCategoryDetails ; 
	
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
	
	@RequestMapping(value="/getOnlybrands" , method=RequestMethod.GET)
	public @ResponseBody List<BrandsOnly> getBrands()
	{
		return onlyBrands.onlyBrands();
	}
	
	@RequestMapping(value="/getCategoryType" , method=RequestMethod.GET)
	public @ResponseBody List<HeaderSubcategoryDetails> getCategoryAndSubCategoryDetails(@RequestParam("categoryID_1") int categoryID_1 , @RequestParam("categoryID_2") int categoryID_2)
	{
		return categoryAndSubCategoryDetails.getDetails(categoryID_1, categoryID_2);
	}
	
	@RequestMapping(value="/getCategoryOfOnlyOneType" , method=RequestMethod.GET)
	public @ResponseBody List<HeaderSubcategoryDetails> getCategoryAndSubCategoryDetails(@RequestParam("categoryID_1") int categoryID_1)
	{
		return categoryAndSubCategoryDetails.getDetails(categoryID_1);
	}
}
