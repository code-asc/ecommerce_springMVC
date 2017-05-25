package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.AdminCountryCount;
import com.app.model.HighestSoldItem;
import com.app.repository.AdminPageQuery;
import com.app.repository.ProductAndBrandDetails;

@Service
public class HighestSoldDetails implements HighestSoldInfo {
	
	@Autowired
	ProductAndBrandDetails details;
	
	@Autowired
	AdminPageQuery info;
	
	/* (non-Javadoc)
	 * @see com.app.service.HighestSoldInfo#getHighestSold()
	 */
	@Override
	public List<HighestSoldItem> getHighestSold()
	{
		return details.highestSoldProduct(); 
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.HighestSoldInfo#getHighestSoldByCountry()
	 */
	@Override
	public List<AdminCountryCount> getHighestSoldByCountry()
	{
		return info.countCountry();
	}
	
}
