package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.AddressInfo;
import com.app.service.CartBuy;
import com.app.service.CartCount;
import com.app.service.SingleBuy;


@Controller
public class AddressDetailsController {
	
	@Autowired
	AddressInfo addressInfo;
	
	@Autowired
	SingleBuy singleBuy;
	
	@Autowired
	CartBuy cartBuy;
	
	@Autowired
	CartCount cartCount;
	
	@RequestMapping(value = "/addressConfirm" , method = RequestMethod.GET)
	public String showUserDefaultAddress(Model model ,  HttpSession session , @RequestParam("buy") String option)
	{
	
		if(session.getAttribute("isUserLoggedIn") != null)
		{
			model.addAttribute("addressQuery" , addressInfo.getUserDefaultAddress((int)session.getAttribute("userID"), "default"));

				
					 if(("singleBuy").compareTo(option) == 0)
							{
								System.out.println("allow single buy....wow");
								model.addAttribute("allowSingleBuy" , true);
							}
					 else if(("cartBuy").compareTo("option") == 0) 
							{
								System.out.println("dont allow single buy....wow");
								model.addAttribute("allowSingleBuy" , false);
							}
					
				
			return "addressConfirm";
		}
		else{
				return "index";
		}
	}
	
	@RequestMapping(value = "/payment" , method = RequestMethod.GET)
	public String showPayment(Model model ,  HttpSession session , @RequestParam("buy") String option)
	{
		if(option.length() != 0)
		{
			option=option.trim();
		}
		if(session.getAttribute("isUserLoggedIn") != null)
		{

			 if(("singleBuy").compareTo(option) == 0)
							{
								singleBuy.singleBuyProduct((int)session.getAttribute("userID") , (int)session.getAttribute("productID") , 0);
							}
			 else if(("cartBuy").compareTo(option) == 0) 
							{
								long identityCol = cartBuy.cartBuyProduct((int)session.getAttribute("userID") , 0);
								cartBuy.setOrderID((int)session.getAttribute("userID") , identityCol);
								session.setAttribute("cartCount", cartCount.getCartCount((int)session.getAttribute("userID")));;
								
							}
					
				
			return "paymentProcessing";
		}else{
				return "index";
		}
	}
}
