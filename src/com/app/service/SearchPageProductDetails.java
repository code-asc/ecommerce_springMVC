package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ProductDetails;
import com.app.repository.ProductAndBrandDetails;

@Service
public class SearchPageProductDetails {
	
	@Autowired
	ProductAndBrandDetails details;
	
	public List<ProductDetails> getSearchDetails(String brand)
	{
		return details.productInfoForSearchPage(brand);
	}
	
	public List<ProductDetails> getSearchDetails()
	{
		return details.productInfoForSearchPage();
	}

}
