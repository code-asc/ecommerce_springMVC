package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CartProductInfo;
import com.app.model.UserAddress;
import com.app.repository.AddressDetails;
import com.app.repository.UserCartDetails;

@Service
public class UserCartBuy implements CartBuy {
	
	@Autowired
	UserCartDetails userCartDetails;
	
	@Autowired
	AddressDetails addressDetails;
	
	/* (non-Javadoc)
	 * @see com.app.service.CartBuy#cartBuyProduct(int, int)
	 */
	@Override
	public long cartBuyProduct(int userID , int addressID)
	{
		long identityCol = 0;
		String addressStatus = "default";
		try{
			userCartDetails.updateOrderDetailsBasedOnStatus(userID);
			if(addressID > 0){
				identityCol = userCartDetails.setOrder(userID, addressID);
			}else{
				List<UserAddress> addressList = addressDetails.userAddressDetails(userID , addressStatus);
				identityCol = userCartDetails.setOrder(userID , addressList.get(0).getAddressID());
			}
			userCartDetails.updateCartCount(userID);
		}
		catch(Exception e)
		{
			System.out.println("error in UserCartBuy service");
			e.printStackTrace();
		}
		return identityCol;
	}
	
	
	/* (non-Javadoc)
	 * @see com.app.service.CartBuy#setOrderID(int, long)
	 */
	@Override
	public void setOrderID(int userID , long orderID)
	{
		String productStatus = "ordered";
		try{
		List<CartProductInfo> productList = userCartDetails.getCartProductDetails(userID , orderID , productStatus);
		for(CartProductInfo info : productList)
		{
			userCartDetails.updateProductInCart(info.getProductID() , info.getQuantity());
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
