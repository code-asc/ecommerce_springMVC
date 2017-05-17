package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ProductDetails;
import com.app.repository.ProductAndBrandDetails;

@Service
public class GetProductDetails {
	
	@Autowired
	ProductAndBrandDetails productDetailsOnly;
	
	public List<ProductDetails> getOnlyProductDetails(int subCategoryID)
	{
		return productDetailsOnly.productDetailsUsingSubCategoryID(subCategoryID);
	}
	
	public List<ProductDetails> getOnlyProductDetailsByProductID(int productID)
	{
		return productDetailsOnly.productDetailsUsingProductID(productID);
	}
	
	public List<ProductDetails> getSuggestedProducts(int productID , int subCategoryID)
	{
		return productDetailsOnly.similarProducts(productID, subCategoryID);
	}
}
