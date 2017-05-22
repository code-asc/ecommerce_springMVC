package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.UpdateAddress;
import com.app.repository.AddressDetails;
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
	
	@Autowired
	SetDefaultAddress setDefaultAddress;
	
	@RequestMapping(value = "/addressConfirm" , method = RequestMethod.GET)
	public String showUserDefaultAddress(Model model ,  HttpSession session , @RequestParam("buy") String option)
	{
	
		if(session.getAttribute("isUserLoggedIn") != null)
		{
			model.addAttribute("addressQuery" , addressInfo.getUserDefaultAddress((int)session.getAttribute("userID"), "default"));

				
					 if(("singleBuy").compareTo(option) == 0)
							{
								model.addAttribute("allowSingleBuy" , true);
							}
					 else if(("cartBuy").compareTo("option") == 0) 
							{
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
	
	
	@RequestMapping(value = "address" , method = RequestMethod.GET)
	public String getNewAddress(@ModelAttribute("addressField") UpdateAddress updateAddress , @RequestParam("buy") String buyType)
	{
		return "address";
	}
	
	@RequestMapping(value = "address" , method = RequestMethod.POST)
	public String postNewAddress(@ModelAttribute("addressField") UpdateAddress updateAddress ,  @RequestParam("buy") String buyType , HttpSession session)
	{
		String country = updateAddress.getCountry();
		String state = updateAddress.getState();
		String city = updateAddress.getCity();
		String address = updateAddress.getAddress();
		String address2 = updateAddress.getAddress2();
		String zipCode = updateAddress.getPincode();
		setDefaultAddress.setNewDefault((int)session.getAttribute("userID"), country, state, city, address, address2, zipCode);
		return "redirect:addressConfirm.html?buy="+buyType;
	}
}
