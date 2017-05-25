package com.app.service;

import java.util.List;

import com.app.model.AdminCountryCount;
import com.app.model.HighestSoldItem;

public interface HighestSoldInfo {

	public abstract List<HighestSoldItem> getHighestSold();

	public abstract List<AdminCountryCount> getHighestSoldByCountry();

}