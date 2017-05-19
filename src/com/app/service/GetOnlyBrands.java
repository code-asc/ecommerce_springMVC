package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.BrandsOnly;
import com.app.repository.ProductAndBrandDetails;


@Service
public class GetOnlyBrands implements BrandInfo {

	@Autowired
	ProductAndBrandDetails getOnlyBrands;
	
	/* (non-Javadoc)
	 * @see com.app.service.BrandInfo#onlyBrands()
	 */
	@Override
	public List<BrandsOnly> onlyBrands()
	{
		return getOnlyBrands.brandOnly();
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.BrandInfo#onlyBrands(int)
	 */
	@Override
	public List<BrandsOnly> onlyBrands(int subCategoryID)
	{
		return getOnlyBrands.getBrandBySubCategory(subCategoryID);
	}
}
