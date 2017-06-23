package com.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserLogin {

	public abstract boolean isUserValid(String email, String password,
			HttpSession session, HttpServletRequest request);

	public abstract void getLoggedInUserDetails(String email , HttpSession session);
}