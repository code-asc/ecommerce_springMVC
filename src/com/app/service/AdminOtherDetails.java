package com.app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CategoryType;
import com.app.model.SubCategoryType;
import com.app.repository.AdminEdit;
import com.app.repository.AdminPageQuery;

@Service
public class AdminOtherDetails {
	
	@Autowired
	AdminEdit edit;
	
	@Autowired
	AdminPageQuery _edit;
	
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
	
	public List<SubCategoryType> getSubCategory(int categoryID)
	{
		return edit.getSubCategory(categoryID);
	}
	
	public void updateProduct(int productID , String productDesc , BigDecimal unitPrice , BigDecimal discount , int unitInStock)
	{
		edit.productEdit(productID, productDesc, unitPrice, discount, unitInStock);
	}
	
	public void deleteProduct(int productID)
	{
		edit.productDelete(productID);
	}
	
	public int addProduct(String productName , String productDesc , int supplierID , int subcategoryID , BigDecimal unitPrice , String thumbNail , String thumbNailType , String largePhotoType , String largePhoto , int quantity , BigDecimal discount , int rating , int brandID)
	{
		return _edit.addNewProduct(productName, productDesc, supplierID, subcategoryID, unitPrice, thumbNail, thumbNailType, largePhotoType, largePhoto, quantity, discount, rating, brandID);
	}
	
	public int editProductSinglePage(int productID , String productDesc , BigDecimal unitPrice , int unitInStock , BigDecimal discount , String thumbNailPhoto , String largePhoto)
	{
		return edit.editProductFromUserSinglePage(productID, productDesc, unitPrice, unitInStock, discount, thumbNailPhoto, largePhoto);
	}
}
