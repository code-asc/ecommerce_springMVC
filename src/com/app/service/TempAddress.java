package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.AddressDetails;

@Service
public class TempAddress {
	
	@Autowired
	AddressDetails addressDetails;
	
	public long setTempAddress(int userID , String country , String state , String city , String address , String address2 , String zipCode)
	{
		return addressDetails.setTempAddress(userID, country, state, city, address, address2, zipCode);
	}
}
