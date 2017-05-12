package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.repository.UserDetails;

@Service
public class RegisterNewUser {

	@Autowired
	UserDetails userDetails;
	
	public boolean doUserRegister(String firstName , String middleName , String lastName , String email , String password , String mobile)
	{
		boolean isUserRegistered = false;
		if(!userDetails.checkUserEmailAlreadyExists(email))
		{
		isUserRegistered=userDetails.isUserRegistrationSuccess(firstName ,  middleName ,  lastName ,  email ,  password ,  mobile);
		}
		return isUserRegistered;
	}
}
