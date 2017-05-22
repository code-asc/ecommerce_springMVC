package com.app.controller;

public interface SetDefaultAddress {

	public abstract void setNewDefault(int userID, String country,
			String state, String city, String address, String address2,
			String zipCode);

}