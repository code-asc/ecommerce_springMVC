package com.app.service;

import java.util.List;

import com.app.model.HeaderSubcategoryDetails;

public interface CategoryAndSubInfo {

	public abstract List<HeaderSubcategoryDetails> getDetails(int categoryID_1);

	public abstract List<HeaderSubcategoryDetails> getDetails(int categoryID_1,
			int categoryID_2);

	public abstract List<HeaderSubcategoryDetails> getDetails(int categoryID_1,
			int categoryID_2, int categoryID_3);

}