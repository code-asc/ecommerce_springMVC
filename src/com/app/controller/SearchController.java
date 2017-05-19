package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.model.ProductDetails;
import com.app.service.BrandInfo;
import com.app.service.SearchOptionInfo;

@Controller
@SessionAttributes({"subCategoryID"})
public class SearchController {
	
	@Autowired
	SearchOptionInfo details;
	
	@Autowired
	BrandInfo brands;
	
	@RequestMapping(value="/search" , method=RequestMethod.GET)
	public String postSearchPage(@RequestParam("searchVal") String searchVal , Model model)
	{
		if(searchVal!="" && searchVal != null)
		{
			try{
                    searchVal=searchVal.trim();				
					List<ProductDetails> list = details.getSearchDetails(searchVal);
					model.addAttribute("searchResult" , details.getSearchDetails(searchVal));
					model.addAttribute("formBrands", brands.onlyBrands(list.get(0).getSubCategoryID()));
					model.addAttribute("subCategoryID" , (int)list.get(0).getSubCategoryID());
					return "searchPage";
					
			}catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		return "searchPage";
	}
}
