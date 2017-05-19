package com.app.service;

import java.util.List;

import com.app.model.ProductDetails;

public interface ProductInfo {

	public abstract List<ProductDetails> getOnlyProductDetails(int subCategoryID);

	public abstract List<ProductDetails> getOnlyProductDetailsByProductID(
			int productID);

	public abstract List<ProductDetails> getSuggestedProducts(int productID,
			int subCategoryID);

}