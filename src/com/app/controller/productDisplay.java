package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.ProductDetails;
import com.app.service.GetOnlyBrands;
import com.app.service.GetProductDetails;

@Controller
public class productDisplay {
	
	@Autowired
	GetOnlyBrands brands;
	
	@Autowired
	GetProductDetails getProductDetails;
	
	@RequestMapping(value="/user_action" , method=RequestMethod.GET)
	public String getProductDisplayPage(@RequestParam("subCategoryID") int subCategoryID , Model model)
	{
		model.addAttribute("formBrands", brands.onlyBrands(subCategoryID));
		model.addAttribute("product", getProductDetails.getOnlyProductDetails(subCategoryID));
		return "user_action";
	}
	
	
	@RequestMapping(value="/getProductsBySubCategoryID" , method=RequestMethod.GET)
	public @ResponseBody List<ProductDetails> returnBrands(@RequestParam("subCategoryID") int subCategoryID)
	{
		return getProductDetails.getOnlyProductDetails(subCategoryID);
	}
}
