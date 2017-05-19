package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.HeaderSubcategoryDetails;
import com.app.repository.CategoryAndSubCategoryDetails;

@Service
public class GetCategoryAndSubCategoryDetails implements CategoryAndSubInfo    {

	@Autowired
	CategoryAndSubCategoryDetails details;
	
	/* (non-Javadoc)
	 * @see com.app.service.CategoryAndSubInfo#getDetails(int)
	 */
	@Override
	public List<HeaderSubcategoryDetails> getDetails(int categoryID_1)
	{
		return details.subCategoryDetails(categoryID_1);
	}

	
	/* (non-Javadoc)
	 * @see com.app.service.CategoryAndSubInfo#getDetails(int, int)
	 */
	@Override
	public List<HeaderSubcategoryDetails> getDetails(int categoryID_1 , int categoryID_2)
	{
		return details.subCategoryDetails(categoryID_1 , categoryID_2);
	}
	
	
	/* (non-Javadoc)
	 * @see com.app.service.CategoryAndSubInfo#getDetails(int, int, int)
	 */
	@Override
	public List<HeaderSubcategoryDetails> getDetails(int categoryID_1 , int categoryID_2 , int categoryID_3)
	{
		return details.subCategoryDetails(categoryID_1 , categoryID_2 , categoryID_3);
	}
}
