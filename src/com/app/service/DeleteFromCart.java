package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.UserCartDetails;

@Service
public class DeleteFromCart implements DeleteCart {
	
	@Autowired
	UserCartDetails cartDetails;
	
	/* (non-Javadoc)
	 * @see com.app.service.DeleteCart#deleteCartDetails(int, int)
	 */
	@Override
	public void deleteCartDetails(int userID , int detailID)
	{
		cartDetails.deleteProductFromCart(userID, detailID);
	}
}
