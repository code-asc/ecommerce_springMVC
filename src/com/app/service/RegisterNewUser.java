package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.repository.UserDetails;

@Service
public class RegisterNewUser implements RegisterUser {

	@Autowired
	UserDetails userDetails;
	
	/* (non-Javadoc)
	 * @see com.app.service.RegisterUser#doUserRegister(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean doUserRegister(String firstName , String middleName , String lastName , String email , String password , String mobile)
	{
		boolean isUserRegistered = false;
		if(!userDetails.checkUserEmailAlreadyExists(email))
		{
			ShaPasswordEncoder encoder = new ShaPasswordEncoder(512);
			String encryptedPassword = encoder.encodePassword(password, null);
			isUserRegistered=userDetails.isUserRegistrationSuccess(firstName ,  middleName ,  lastName ,  email ,  encryptedPassword ,  mobile);
		}
		return isUserRegistered;
	}
}
