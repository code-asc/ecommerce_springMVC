package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.UserCartDetails;



@Service
public class CartCountInfo implements CartCount {
	
	@Autowired
	UserCartDetails info;
	
	/* (non-Javadoc)
	 * @see com.app.service.CartCount#getCartCount(int)
	 */
	@Override
	public int getCartCount(int userID)
	{
		return info.updateCartCount(userID);
	}
}
