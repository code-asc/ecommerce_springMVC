package com.app.service;

import java.util.List;

import com.app.model.AdminCategoryCount;
import com.app.model.AdminCountryCount;
import com.app.model.AdminCustomerCount;
import com.app.model.AdminProductCount;
import com.app.model.AdminShippingCount;
import com.app.model.AdminSubCategoryCount;
import com.app.model.AdminSupplierCount;

public interface AdminPageAllInfo {

	public abstract List<AdminCountryCount> countryCountList();

	public abstract List<AdminProductCount> productCountList();

	public abstract List<AdminCustomerCount> customerCountList();

	public abstract List<AdminCategoryCount> categoryCountList();

	public abstract List<AdminSubCategoryCount> categorySubCountList();

	public abstract List<AdminSupplierCount> supplierCountList();

	public abstract List<AdminShippingCount> shippingCountList();
	
	public abstract int onlineUsers();
	
	public void changeUserStatusToOffline(int userID);
	
	public void removeUserOnline(int userID);
}