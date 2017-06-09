package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

/**
 * The productDisplay class provides the product details based on filter , category and subcategory
 */
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
	
	
	/**
	 *  getProductDisplayPage method provides user the product details in userAction page .
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 * @param subCategoryID of type integer. It is provided by the URL
	 */
	@RequestMapping(value = "/user_action" , method = RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String getProductDisplayPage(@RequestParam("subCategoryID") int subCategoryID , Model model , HttpSession session )
	{
		model.addAttribute("subCategoryID" ,subCategoryID );
		model.addAttribute("formBrands", brands.onlyBrands(subCategoryID));
		model.addAttribute("product", getProductDetails.getOnlyProductDetails(subCategoryID));
		return "user_action";
	}
	
	
	/**
	 * getProductDisplayPageByProductID method provides user the product details in userAction page .
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 * @param productID of type integer. It is provided by the URL
	 */
	@RequestMapping(value="/user_action_single" , method=RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String getProductDisplayPageByProductID(@RequestParam("productID") int productID , Model model , HttpSession session)
	{
		model.addAttribute("productID" , productID);
		model.addAttribute("retriveProduct" , getProductDetails.getOnlyProductDetailsByProductID(productID));
		model.addAttribute("suggestProduct" , getProductDetails.getSuggestedProducts(productID, (int)session.getAttribute("subCategoryID")));
		
		return "user_action_single";
	}
	
	
	/**
	 * returnFilteredProducts method provides user the product details based on filters .
	 * @param brandID of type integer. It is used to modify the view accordingly.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 * @param discount of type BigDecimal. It is provided by the URL
	 */
	@RequestMapping(value="/filterProducts" , method=RequestMethod.GET)
	public @ResponseBody List<ProductDetails> returnFilteredProducts(@RequestParam("brandID") String brandID , @RequestParam("discount") String discount , HttpSession session)
	{
		return filteredProducts.getFilteredProducts(brandID, discount, (int)session.getAttribute("subCategoryID"));
	}
	
	
	/**
	 * returnBrands method provides user the brand details.
	 * @param productID of type integer.
	 */
	@RequestMapping(value="/getProductsByProductID" , method=RequestMethod.GET)
	public @ResponseBody List<ProductDetails> returnBrands(@RequestParam("productID") int productID)
	{
		return getProductDetails.getOnlyProductDetailsByProductID(productID);
	}
	
	
	/**
	 * getOrderDetails method provides user the previously purchased details along with pagenation.
	 * @param start of type integer.
	 * @param page of type integer.
	 * @param model of type Model.
	 * @param session of type HttpSession.
	 */
	@RequestMapping(value="/orderDetails" , method = RequestMethod.GET , params={"start" , "page"} , produces = MediaType.TEXT_HTML_VALUE)
	public String getOrderDetails(@RequestParam("start") int start , @RequestParam("page") int page , Model model , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && (boolean)session.getAttribute("isUserLoggedIn"))
		{
		int limitTo = start + 4;
		model.addAttribute("page" , page);
		model.addAttribute("total" , purchaseInfo.getTotalPurchaseList((int)session.getAttribute("userID")).size());
		model.addAttribute("detailQuery", purchaseInfo.getPurchaseDetails((int)session.getAttribute("userID"), start , limitTo) );
		return "orderDetails";
		}
		else
		{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 * orderDetails method provides user the previously purchased details.
	 * @param page of type integer.
	 * @param model of type Model.
	 * @param session of type HttpSession.
	 */
	@RequestMapping(value="/orderDetails" , method = RequestMethod.GET , params={"page"} , produces = MediaType.TEXT_HTML_VALUE)
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
			return "redirect:signin.html";
		}
	}
	
/*	@RequestMapping(value="/getPurchaseInfo" , method=RequestMethod.GET)
	public @ResponseBody List<PurchaseHistoryModel> getPurchaseInfo(HttpSession session)
	{
		//return purchaseInfo.getPurchaseDetails((int)session.getAttribute("userID"), 1, 4);
		return purchaseInfo.getTotalPurchaseList((int)session.getAttribute("userID"));
	}*/
	
	
}
