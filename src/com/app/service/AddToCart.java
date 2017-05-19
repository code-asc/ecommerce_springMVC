package com.app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CartDisplayList;
import com.app.model.OrderDetailsInfo;
import com.app.model.ProductDetails;
import com.app.repository.ProductAndBrandDetails;
import com.app.repository.UserCartDetails;

@Service
public class AddToCart implements CartInfo {
	
	@Autowired
	UserCartDetails userCartDetails;
	
	@Autowired
	ProductAndBrandDetails productInfo;
	
	@Autowired
	CartDisplayList displayList;
	
	/* (non-Javadoc)
	 * @see com.app.service.CartInfo#addToCart(java.lang.String, int, int)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see com.app.service.CartInfo#getCartList(java.lang.String, int)
	 */
	@Override
	public List<CartDisplayList> getCartList(String status , int userID)
	{
		return userCartDetails.cartDisplayList(status, userID);
	}
	
	
	/* (non-Javadoc)
	 * @see com.app.service.CartInfo#incrementProduct(int, int)
	 */
	@Override
	public void incrementProduct(int userID , int detailID)
	{
		userCartDetails.incrementQuantityInDatabase(userID, detailID);
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.CartInfo#decrementProduct(int, int)
	 */
	@Override
	public void decrementProduct(int userID , int detailID)
	{
		userCartDetails.decrementQuantityInDatabase(userID, detailID);
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.CartInfo#getOrderDetailsInfo(int, int)
	 */
	@Override
	public List<OrderDetailsInfo> getOrderDetailsInfo(int detailID , int userID)
	{
		List<Integer> list = userCartDetails.getOrderPriceAndQty(userID);
		List<OrderDetailsInfo> _list = userCartDetails.orderDetailsInfo(detailID);
		try{
	
		int sum = (int)list.get(1);
		int totalCount = (int)list.get(0);
		
		System.out.println("Sum : "+sum + "\n" + "totalCount : " + totalCount);
		_list.get(0).setSum(sum);
		_list.get(0).setTotalCount(totalCount);
		_list.get(0).setTotalPriceForEachProduct((_list.get(0).getQuantity())*(_list.get(0).getDetailPrice().intValue()));
		
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return _list;
	}
}
