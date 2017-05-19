package com.app.service;

import java.util.List;

import com.app.model.ProductDetails;

public interface SearchOptionInfo {

	public abstract List<ProductDetails> getSearchDetails(String brand);

	public abstract List<ProductDetails> getSearchDetails();

}