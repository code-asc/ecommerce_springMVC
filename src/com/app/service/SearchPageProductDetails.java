package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ProductDetails;
import com.app.repository.ProductAndBrandDetails;

@Service
public class SearchPageProductDetails implements SearchOptionInfo {
	
	@Autowired
	ProductAndBrandDetails details;
	
	/* (non-Javadoc)
	 * @see com.app.service.SearchOptionInfo#getSearchDetails(java.lang.String)
	 */
	@Override
	public List<ProductDetails> getSearchDetails(String brand)
	{
		return details.productInfoForSearchPage(brand);
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.SearchOptionInfo#getSearchDetails()
	 */
	@Override
	public List<ProductDetails> getSearchDetails()
	{
		return details.productInfoForSearchPage();
	}

}
