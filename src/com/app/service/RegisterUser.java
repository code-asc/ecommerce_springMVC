package com.app.service;

public interface RegisterUser {

	public abstract boolean doUserRegister(String firstName, String middleName,
			String lastName, String email, String password, String mobile);

}