package com.app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ProductDetails;
import com.app.repository.ProductAndBrandDetails;
import com.app.repository.UserCartDetails;

@Service
public class AddToCart {
	
	@Autowired
	UserCartDetails userCartDetails;
	
	@Autowired
	ProductAndBrandDetails productInfo;
	
	public void addToCart(String status , int userID , int productID)
	{
		int detailID=userCartDetails.getDetailID(status, userID, productID);
		if(detailID == 0)
		{
			try{
			List<ProductDetails> list;
			BigDecimal productDiscount;
			int supplierID;
			String _status="addedToCart";
			list=productInfo.productDetailsUsingProductID(productID);
			productDiscount=list.get(0).getAfterDiscount();;
			supplierID=list.get(0).getSupplierID();
			userCartDetails.setOrderDetails(_status, userID, productID, supplierID, productDiscount);
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}else{
			userCartDetails.updateOrderDetails(userID, productID);
		}
		
		//use updateCartCount();
	}
}
