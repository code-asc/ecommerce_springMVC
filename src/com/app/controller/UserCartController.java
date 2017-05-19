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

import com.app.model.OrderDetailsInfo;
import com.app.service.CartInfo;
import com.app.service.DeleteCart;


@Controller
public class UserCartController {
	
	@Autowired
	CartInfo addToCartOption ;
	
	@Autowired
	DeleteCart deleteCart ;
	
	@RequestMapping(value = "/addToCart" , method = RequestMethod.GET)
	public @ResponseBody void addProductToCart( HttpSession session)
	{
		addToCartOption.addToCart("addedToCart" , (int)session.getAttribute("userID"), (int)session.getAttribute("productID"));
	}
	
	@RequestMapping(value = "/userCart" , method = RequestMethod.GET)
	public  String getUserCartInfo(Model model , HttpSession session)
	{
		model.addAttribute("retriveCart" , addToCartOption.getCartList("addedToCart" ,  (int)session.getAttribute("userID")));
		return "userCart";
	}
	
	@RequestMapping(value = "/incrementQuantity" , method = RequestMethod.GET)
	public @ResponseBody List<OrderDetailsInfo> incrementQuantity(@RequestParam("detailID") int detailID , HttpSession session)
	{
		addToCartOption.incrementProduct((int)session.getAttribute("userID") , detailID);
		return addToCartOption.getOrderDetailsInfo(detailID , (int)session.getAttribute("userID"));
	}
	
	@RequestMapping(value = "/decrementQuantity" , method = RequestMethod.GET)
	public @ResponseBody List<OrderDetailsInfo> decrementQuantity(@RequestParam("detailID") int detailID , HttpSession session)
	{
		addToCartOption.decrementProduct((int)session.getAttribute("userID") , detailID);
		return addToCartOption.getOrderDetailsInfo(detailID , (int)session.getAttribute("userID"));
	}
	
	@RequestMapping(value="deleteFromCart" , method = RequestMethod.GET)
	public String deleteCartItems(@RequestParam("removeFromCart") int detailID , HttpSession session)
	{
		deleteCart.deleteCartDetails((int)session.getAttribute("userID") , detailID);
		return "redirect:userCart.html";
	}
}
