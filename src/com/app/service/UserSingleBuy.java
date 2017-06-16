package com.app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ProductDetails;
import com.app.model.UserAddress;
import com.app.repository.AddressDetails;
import com.app.repository.ProductAndBrandDetails;
import com.app.repository.UserCartDetails;


@Service
public class UserSingleBuy implements SingleBuy {

	@Autowired
	ProductAndBrandDetails details;
	
	@Autowired
	UserCartDetails cartDetails;
	
	@Autowired
	AddressDetails addressDetails;
	
	
	/* (non-Javadoc)
	 * @see com.app.service.SingleBuy#singleBuyProduct(int, int, int)
	 */
	@Override
	@SuppressWarnings("all")
	public void singleBuyProduct(int userID , int productID , long addressID)
	{
		
		BigDecimal afterDiscount;
		String status = "progress";
		String addressStatus = "default";
		int supplierID;
		long temp = 0;
		
		try{
		List<ProductDetails> productList = details.productDetailsUsingProductID(productID);
		afterDiscount=productList.get(0).getAfterDiscount();
		supplierID=productList.get(0).getSupplierID();
		cartDetails.setOrderDetails(status , userID , productID , supplierID , afterDiscount);
		
		if(addressID > 0){
			temp = cartDetails.setOrder(userID , addressID);
		}else{
			List<UserAddress> addressList = addressDetails.userAddressDetails(userID , addressStatus);
			temp = cartDetails.setOrder(userID , addressList.get(0).getAddressID());

		}
		
		details.updateProductQtyOnOrder(productID);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
