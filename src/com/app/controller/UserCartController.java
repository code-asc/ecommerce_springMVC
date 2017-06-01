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
import com.app.service.CartCount;
import com.app.service.CartInfo;
import com.app.service.DeleteCart;


@Controller
/**
 * The UserCartController class allows the user to check all the cart details
 */
public class UserCartController {
	
	@Autowired
	CartInfo addToCartOption ;
	
	@Autowired
	DeleteCart deleteCart ;
	
	@Autowired
	CartCount cartCount;
	
	
	/**
	 * addProductToCart method allows the user to add products to cart.
	 * @param session of type HttpSession
	 */
	@RequestMapping(value = "/addToCart" , method = RequestMethod.GET)
	public @ResponseBody void addProductToCart( HttpSession session)
	{
		try
		{
			addToCartOption.addToCart("addedToCart" , (int)session.getAttribute("userID"), (int)session.getAttribute("productID"));
			int count = cartCount.getCartCount((int)session.getAttribute("userID"));
			session.setAttribute("cartCount" , count);
			System.out.print("In addToCart method : "+count);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * getUserCartInfo method allows the user to get products information from cart.
	 * @param model of type Model
	 * @param session of type HttpSession
	 */
	@RequestMapping(value = "/userCart" , method = RequestMethod.GET)
	public  String getUserCartInfo(Model model , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && (boolean)session.getAttribute("isUserLoggedIn"))
		{
		model.addAttribute("retriveCart" , addToCartOption.getCartList("addedToCart" ,  (int)session.getAttribute("userID")));
		return "userCart";
		}else{
			 return "redirect:signin.html";
		}
	}
	
	
	/**
	 * incrementQuantity method allows the user to increment products in cart.
	 * @param detailID of type integer
	 * @param session of type HttpSession
	 */
	@RequestMapping(value = "/incrementQuantity" , method = RequestMethod.GET)
	public @ResponseBody List<OrderDetailsInfo> incrementQuantity(@RequestParam("detailID") int detailID , HttpSession session)
	{
		try
		{
			addToCartOption.incrementProduct((int)session.getAttribute("userID") , detailID);
			int count = cartCount.getCartCount((int)session.getAttribute("userID"));
			session.setAttribute("cartCount" , count);		
			System.out.print("In incrementQuantity method : "+count);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return addToCartOption.getOrderDetailsInfo(detailID , (int)session.getAttribute("userID"));
	}
	
	/**
	 * decrementQuantity method allows the user to decrement products in cart.
	 * @param detailID of type integer
	 * @param session of type HttpSession
	 */
	@RequestMapping(value = "/decrementQuantity" , method = RequestMethod.GET)
	public @ResponseBody List<OrderDetailsInfo> decrementQuantity(@RequestParam("detailID") int detailID , HttpSession session)
	{
		try
		{
			addToCartOption.decrementProduct((int)session.getAttribute("userID") , detailID);
			int count = cartCount.getCartCount((int)session.getAttribute("userID"));
			session.setAttribute("cartCount" , count);		
			System.out.print("In decrementQuantity method : "+count);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return addToCartOption.getOrderDetailsInfo(detailID , (int)session.getAttribute("userID"));
	}
	
	/**
	 * deleteCartItems method allows the user to delete products in cart.
	 * @param detailID of type integer
	 * @param session of type HttpSession
	 */
	@RequestMapping(value="deleteFromCart" , method = RequestMethod.GET)
	public String deleteCartItems(@RequestParam("removeFromCart") int detailID , HttpSession session)
	{
		try
		{
			deleteCart.deleteCartDetails((int)session.getAttribute("userID") , detailID);
			int count = cartCount.getCartCount((int)session.getAttribute("userID"));
			session.setAttribute("cartCount" , count);		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "redirect:userCart.html";
	}
	
}
