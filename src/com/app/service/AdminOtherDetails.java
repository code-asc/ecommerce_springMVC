package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CategoryType;
import com.app.model.SubCategoryType;
import com.app.repository.AdminEdit;

@Service
public class AdminOtherDetails {
	
	@Autowired
	AdminEdit edit;
	
	public int addBrand(String brandName)
	{
		return edit.addBrandToDatabase(brandName);
	}
	
	public int addCategory(String categoryType)
	{
		return edit.addCategoryToDatabase(categoryType);
	}
	
	public int addSubCategory(int categoryID , String subCategoryType)
	{
		return edit.addSubCategoryToDatabase(categoryID, subCategoryType);
	}
	
	public List<CategoryType> getCategory()
	{
		return edit.getCategory();
	}
	
	public List<SubCategoryType> getSubCategory()
	{
		return edit.getSubCategory();
	}
}
