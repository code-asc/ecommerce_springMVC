package com.app.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.UserDetails;

@Service
public class DoUserLogin {

	@Autowired
	UserDetails userDetails;

	public boolean isUserValid(String email, String password , HttpSession session , HttpServletRequest request) {
		
		
		ResultSet rs = userDetails.doLogin(email, password);
		String userFirstName="";
		String userLastName="";
		String userMiddleName="";
		String userFullName="";
		int userID=-1;
		int userCount = 0;
		boolean returnVal=false;
		try {
			while (rs.next()) {
				
				userFirstName=rs.getString("userFirstName");
				userLastName=rs.getString("userLastName");
				userMiddleName=rs.getString("userMiddleName");
				userID=rs.getInt("userID");
				userCount++;
				
			}
			
			if(userCount==1)
			{
				session=request.getSession(true);
				userFullName=userFirstName+" "+ userMiddleName+" "+userLastName;
				session.setMaxInactiveInterval(11*60);
				session.setAttribute("userID", userID);
				session.setAttribute("userFullName", userFullName);
				session.setAttribute("isUserLoggedIn", true);
				returnVal=true;
			}
			
			System.out.println("userCount :: "+userCount);
			System.out.println("userID :: "+session.getAttribute("userFullName"));
			System.out.println("session age :: "+session.getMaxInactiveInterval());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnVal;
	
	}
}
