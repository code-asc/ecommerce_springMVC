package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.AddressDetails;

@Service
public class SetNewDefaultAddress implements SetDefaultAddress {
	
	@Autowired
	AddressDetails addressDetails;
	
	/* (non-Javadoc)
	 * @see com.app.controller.SetDefaultAddress#setNewDefault(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setNewDefault(int userID , String country, String state, String city, String address, String address2, String zipCode)
	{
		addressDetails.updateAddressStatusToNull(userID);
		addressDetails.setNewDefaultAddress(userID, country, state, city, address, address2, zipCode);
	}
}
