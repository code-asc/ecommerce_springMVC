package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.model.ProductDetails;
import com.app.service.FilteredProduct;
import com.app.service.GetOnlyBrands;
import com.app.service.GetProductDetails;

@Controller
@SessionAttributes({"productID" , "subCategoryID"})
public class productDisplay {
	
	@Autowired
	GetOnlyBrands brands;
	
	@Autowired
	GetProductDetails getProductDetails;
	
	@Autowired
	FilteredProduct filteredProducts;
	
	@RequestMapping(value = "/user_action" , method = RequestMethod.GET)
	public String getProductDisplayPage(@RequestParam("subCategoryID") int subCategoryID , Model model , HttpSession session )
	{
		model.addAttribute("subCategoryID" ,subCategoryID );
		model.addAttribute("formBrands", brands.onlyBrands(subCategoryID));
		model.addAttribute("product", getProductDetails.getOnlyProductDetails(subCategoryID));
		return "user_action";
	}
	
	
	@RequestMapping(value="/user_action_single" , method=RequestMethod.GET)
	public String getProductDisplayPageByProductID(@RequestParam("productID") int productID, Model model)
	{
		model.addAttribute("productID" , productID);
		model.addAttribute("retriveProduct" , getProductDetails.getOnlyProductDetailsByProductID(productID));
		return "user_action_single";
	}
	
	
	@RequestMapping(value="/filterProducts" , method=RequestMethod.GET)
	public @ResponseBody List<ProductDetails> returnFilteredProducts(@RequestParam("brandID") String brandID , @RequestParam("discount") String discount , HttpSession session)
	{
		return filteredProducts.getFilteredProducts(brandID, discount, (int)session.getAttribute("subCategoryID"));
	}
	
	
	@RequestMapping(value="/getProductsByProductID" , method=RequestMethod.GET)
	public @ResponseBody List<ProductDetails> returnBrands(@RequestParam("productID") int productID)
	{
		return getProductDetails.getOnlyProductDetailsByProductID(productID);
	}
	
	
}
