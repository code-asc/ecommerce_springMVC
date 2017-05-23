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
import com.app.model.PurchaseHistoryModel;
import com.app.repository.PurchaseHistory;
import com.app.service.BrandInfo;
import com.app.service.FilterProductList;
import com.app.service.ProductInfo;
import com.app.service.PurchaseInfo;

@Controller
@SessionAttributes({"productID" , "subCategoryID"})
public class productDisplay {
	
	@Autowired
	BrandInfo brands;
	
	@Autowired
	ProductInfo getProductDetails;
	
	@Autowired
	FilterProductList filteredProducts;
	
	@Autowired
	PurchaseInfo purchaseInfo;
	
	@Autowired
	PurchaseHistory purchase;
	
	@RequestMapping(value = "/user_action" , method = RequestMethod.GET)
	public String getProductDisplayPage(@RequestParam("subCategoryID") int subCategoryID , Model model , HttpSession session )
	{
		model.addAttribute("subCategoryID" ,subCategoryID );
		model.addAttribute("formBrands", brands.onlyBrands(subCategoryID));
		model.addAttribute("product", getProductDetails.getOnlyProductDetails(subCategoryID));
		return "user_action";
	}
	
	
	@RequestMapping(value="/user_action_single" , method=RequestMethod.GET)
	public String getProductDisplayPageByProductID(@RequestParam("productID") int productID , Model model , HttpSession session)
	{
		model.addAttribute("productID" , productID);
		model.addAttribute("retriveProduct" , getProductDetails.getOnlyProductDetailsByProductID(productID));
		model.addAttribute("suggestProduct" , getProductDetails.getSuggestedProducts(productID, (int)session.getAttribute("subCategoryID")));
		
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
	
	
	@RequestMapping(value="/orderDetails" , method = RequestMethod.GET , params={"start" , "page"})
	public String getOrderDetails(@RequestParam("start") int start , @RequestParam("page") int page , Model model , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && (boolean)session.getAttribute("isUserLoggedIn"))
		{
		int limitTo = start + 4;
		model.addAttribute("total" , purchaseInfo.getTotalPurchaseList((int)session.getAttribute("userID")).size());
		model.addAttribute("detailQuery", purchaseInfo.getPurchaseDetails((int)session.getAttribute("userID"), start , limitTo) );
		return "orderDetails";
		}
		else
		{
			return "index";
		}
	}
	
	
	@RequestMapping(value="/orderDetails" , method = RequestMethod.GET , params={"page"})
	public String getOrderDetails(@RequestParam("page") int page , Model model , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && (boolean)session.getAttribute("isUserLoggedIn"))
		{
		int start = 0;
		int limitTo = 4;
		List<PurchaseHistoryModel> list =  purchaseInfo.getPurchaseDetails((int)session.getAttribute("userID"), start , limitTo);
		model.addAttribute("total" , purchaseInfo.getTotalPurchaseList((int)session.getAttribute("userID")).size());
		model.addAttribute("detailQuery", list);
		model.addAttribute("onThisPage" , list.size());
		model.addAttribute("page" , page);
		return "orderDetails";
		}
		else
		{
			return "index";
		}
	}
	
/*	@RequestMapping(value="/getPurchaseInfo" , method=RequestMethod.GET)
	public @ResponseBody List<PurchaseHistoryModel> getPurchaseInfo(HttpSession session)
	{
		//return purchaseInfo.getPurchaseDetails((int)session.getAttribute("userID"), 1, 4);
		return purchaseInfo.getTotalPurchaseList((int)session.getAttribute("userID"));
	}*/
	
	
}
