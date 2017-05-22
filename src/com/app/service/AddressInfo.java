package com.app.service;

import java.util.List;

import com.app.model.UserAddress;

public interface AddressInfo {

	public abstract List<UserAddress> getUserDefaultAddress(int userID,
			String type);

}