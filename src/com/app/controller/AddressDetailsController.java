package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.UpdateAddress;
import com.app.service.AddressInfo;
import com.app.service.CartBuy;
import com.app.service.CartCount;
import com.app.service.SetDefaultAddress;
import com.app.service.SingleBuy;
import com.app.service.TempAddress;


@Controller

/**
 * The AddressDetailsController class all the user Address Details.
 * User can edit the Address, update the address.
 * It is also used to map the previous purchases with the particular address
 */
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
	
	@Autowired
	TempAddress tempAddress;
	
	
	
	/**
	 *  showUserDefaultAddress method provides user to see his default address.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 * @param option of type String. It is provided by the URL
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/addressConfirm" , method = RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String showUserDefaultAddress(Model model ,  HttpSession session , @RequestParam("buy") String option)
	{
	
		if(session.getAttribute("isUserLoggedIn") != null && (boolean)session.getAttribute("isUserLoggedIn"))
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
				return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  showPayment method is used to check whether the payment is single buy or cart buy.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 * @param option of type String. It is provided by the URL
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/payment" , method = RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
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
				return "redirect:signin.html";
		}
	}
	
	
	
	/**
	 *  getNewAddress method is used to allow the user to update Address.
	 * @param updateAddress of type UpdateAddress.
	 * @param buyType of type String. It is provided by the URL
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "address" , method = RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String getNewAddress(@ModelAttribute("addressField") UpdateAddress updateAddress , @RequestParam("buy") String buyType)
	{
		return "address";
	}
	
	
	/**
	 *  postNewAddress method is used to allow the user to change default Address.
	 * @param updateAddress of type UpdateAddress.
	 * @param buyType of type String. It is provided by the URL
	 * @param session of type HttpSession.
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
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
	
	
	/**
	 *  getEditAddress method is used to allow the user to update default Address.
	 * @param updateAddress of type UpdateAddress.
	 * @param buyType of type String. It is provided by the URL
	 * @param session of type HttpSession.
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "newAddress" , method = RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String getEditAddress(@ModelAttribute("addressField") UpdateAddress updateAddress , @RequestParam("buy") String buyType , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && (boolean)session.getAttribute("isUserLoggedIn"))
		{
		return "address";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	
	/**
	 *  postEditAddress method is used to allow the user to update default Address.
	 * @param updateAddress of type UpdateAddress.
	 * @param buyType of type String. It is provided by the URL
	 * @param session of type HttpSession.
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "newAddress" , method = RequestMethod.POST)
	public String postEditAddress(@ModelAttribute("addressField") UpdateAddress updateAddress , @RequestParam("buy") String buyType , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && (boolean)session.getAttribute("isUserLoggedIn"))
		{
		String country = updateAddress.getCountry();
		String state = updateAddress.getState();
		String city = updateAddress.getCity();
		String address = updateAddress.getAddress();
		String address2 = updateAddress.getAddress2();
		String zipCode = updateAddress.getPincode();
		long addressIdentityCol = tempAddress.setTempAddress((int)session.getAttribute("userID"), country, state, city, address, address2, zipCode);
		if(("singleBuy").compareTo(buyType) == 0)
		{
			singleBuy.singleBuyProduct((int)session.getAttribute("userID") , (int)session.getAttribute("productID") , addressIdentityCol);
		}
		else if(("cartBuy").compareTo(buyType) == 0) 
		{
			long identityCol = cartBuy.cartBuyProduct((int)session.getAttribute("userID") , addressIdentityCol);
			cartBuy.setOrderID((int)session.getAttribute("userID") , identityCol);
			session.setAttribute("cartCount", cartCount.getCartCount((int)session.getAttribute("userID")));;
			
		}


		return "paymentProcessing";
		}else{
			return "redirect:signin.html";
		}
	}
}
