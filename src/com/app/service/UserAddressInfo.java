package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.UserAddress;
import com.app.repository.AddressDetails;

@Service
public class UserAddressInfo implements AddressInfo {
	
	@Autowired
	AddressDetails addressDetails;
	
	/* (non-Javadoc)
	 * @see com.app.service.AddressInfo#getUserDefaultAddress(int, java.lang.String)
	 */
	@Override
	public List<UserAddress>getUserDefaultAddress(int userID , String type)
	{
		return addressDetails.userAddressDetails(userID, type);
	}
}
