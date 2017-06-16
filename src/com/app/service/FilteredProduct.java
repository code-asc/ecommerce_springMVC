package com.app.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ProductDetails;
import com.app.repository.ProductAndBrandDetails;
import com.google.gson.Gson;

@Service
public class FilteredProduct implements FilterProductList {
	
	@Autowired
	ProductAndBrandDetails getProducts;
	
	/* (non-Javadoc)
	 * @see com.app.service.FilterProductList#getFilteredProducts(java.lang.String, java.lang.String, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ProductDetails> getFilteredProducts(String brandID , String discount , int subCategoryID)
	{
		ArrayList<String> brandIDList = new Gson().fromJson(brandID, ArrayList.class);
		ArrayList<String> discountList = new Gson().fromJson(discount, ArrayList.class);
		return getProducts.filteredProducts(brandIDList, discountList, subCategoryID);
	}
}
