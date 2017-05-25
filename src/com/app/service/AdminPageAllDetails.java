package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.AdminCategoryCount;
import com.app.model.AdminCountryCount;
import com.app.model.AdminCustomerCount;
import com.app.model.AdminProductCount;
import com.app.model.AdminShippingCount;
import com.app.model.AdminSubCategoryCount;
import com.app.model.AdminSupplierCount;
import com.app.repository.AdminPageQuery;

@Service
public class AdminPageAllDetails implements AdminPageAllInfo {
	
	@Autowired
	AdminPageQuery query;
	
	/* (non-Javadoc)
	 * @see com.app.service.AdminPageAllInfo#countryCountList()
	 */
	@Override
	public List<AdminCountryCount> countryCountList()
	{
		return query.countCountry();
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.AdminPageAllInfo#productCountList()
	 */
	@Override
	public List<AdminProductCount> productCountList()
	{
		return query.countProduct();
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.AdminPageAllInfo#customerCountList()
	 */
	@Override
	public List<AdminCustomerCount> customerCountList()
	{
		return query.countCustomer();
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.AdminPageAllInfo#categoryCountList()
	 */
	@Override
	public List<AdminCategoryCount> categoryCountList()
	{
		return query.countCategory();
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.AdminPageAllInfo#categorySubCountList()
	 */
	@Override
	public List<AdminSubCategoryCount> categorySubCountList()
	{
		return query.countSubCategory();
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.AdminPageAllInfo#supplierCountList()
	 */
	@Override
	public List<AdminSupplierCount> supplierCountList()
	{
		return query.countSupplier();
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.AdminPageAllInfo#shippingCountList()
	 */
	@Override
	public List<AdminShippingCount> shippingCountList()
	{
		return query.countShipping();
	}
	
}
