package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.BrandsOnly;
import com.app.repository.ProductAndBrandDetails;


@Service
public class GetOnlyBrands {

	@Autowired
	ProductAndBrandDetails getOnlyBrands;
	
	public List<BrandsOnly> onlyBrands()
	{
		return getOnlyBrands.brandOnly();
	}
	
	public List<BrandsOnly> onlyBrands(int subCategoryID)
	{
		return getOnlyBrands.getBrandBySubCategory(subCategoryID);
	}
}
