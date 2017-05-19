package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ProductDetails;
import com.app.repository.ProductAndBrandDetails;

@Service
public class GetProductDetails implements ProductInfo {
	
	@Autowired
	ProductAndBrandDetails productDetailsOnly;
	
	/* (non-Javadoc)
	 * @see com.app.service.ProductInfo#getOnlyProductDetails(int)
	 */
	@Override
	public List<ProductDetails> getOnlyProductDetails(int subCategoryID)
	{
		return productDetailsOnly.productDetailsUsingSubCategoryID(subCategoryID);
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.ProductInfo#getOnlyProductDetailsByProductID(int)
	 */
	@Override
	public List<ProductDetails> getOnlyProductDetailsByProductID(int productID)
	{
		return productDetailsOnly.productDetailsUsingProductID(productID);
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.ProductInfo#getSuggestedProducts(int, int)
	 */
	@Override
	public List<ProductDetails> getSuggestedProducts(int productID , int subCategoryID)
	{
		return productDetailsOnly.similarProducts(productID, subCategoryID);
	}
}
