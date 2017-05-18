package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.service.AddToCart;


@Controller
public class UserCartController {
	
	@Autowired
	AddToCart addToCartOption;
	
	@RequestMapping(value="/addToCart" , method = RequestMethod.GET)
	public @ResponseBody void addProductToCart( HttpSession session)
	{
		addToCartOption.addToCart("addedToCart" , (int)session.getAttribute("userID"), (int)session.getAttribute("productID"));
	}
}
