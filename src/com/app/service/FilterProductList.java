package com.app.service;

import java.util.List;

import com.app.model.ProductDetails;

public interface FilterProductList {

	public abstract List<ProductDetails> getFilteredProducts(String brandID,
			String discount, int subCategoryID);

}