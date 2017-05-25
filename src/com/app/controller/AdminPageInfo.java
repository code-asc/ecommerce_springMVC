package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.AdminCountryCount;
import com.app.model.HighestSoldItem;
import com.app.service.AdminPageAllInfo;
import com.app.service.HighestSoldInfo;


@Controller
public class AdminPageInfo {

	@Autowired
	HighestSoldInfo details;
	
	@Autowired
	AdminPageAllInfo allInfo;
	
	@RequestMapping(value = "/getHighestSoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<HighestSoldItem> getProductHighest()
	{
		return details.getHighestSold();
	}
	
	@RequestMapping(value = "/getCountrySoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<AdminCountryCount> getCountryHighest()
	{
		return details.getHighestSoldByCountry();
	}
	
	@RequestMapping(value = "/admin" , method = RequestMethod.GET)
	public String getAdminPage(Model model)
	{
		model.addAttribute("customer" , allInfo.customerCountList().get(0).getTotal());
		model.addAttribute("product" , allInfo.productCountList().get(0).getTotal());
		model.addAttribute("shipping" , allInfo.shippingCountList().get(0).getTotal());
		model.addAttribute("supplier" , allInfo.supplierCountList().get(0).getTotal());
		model.addAttribute("category", allInfo.supplierCountList().get(0).getTotal());
		model.addAttribute("subCategory" , allInfo.categorySubCountList().get(0).getTotal());
		return "admin";
	}
}
