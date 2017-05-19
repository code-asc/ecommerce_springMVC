package com.app.service;

import java.util.List;

import com.app.model.BrandsOnly;

public interface BrandInfo {

	public abstract List<BrandsOnly> onlyBrands();

	public abstract List<BrandsOnly> onlyBrands(int subCategoryID);

}