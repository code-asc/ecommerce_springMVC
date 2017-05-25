package com.app.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.LoggedInUserInfo;

@Repository
public class UserDetails {
	
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	
	public List<LoggedInUserInfo> doLogin(String userEmail, String userPassword) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<LoggedInUserInfo> list = new ArrayList<>();
		
		//int userCount = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sqlStatement = "select userID , userFirstName , userLastName , userMiddleName , userEmail , userProfilePhoto , roles from Customer where userEmail=? AND userPassword=hashbytes('sha2_512',convert(varchar, ?))";
		    stmt = con.prepareStatement(sqlStatement);
			stmt.setString(1, userEmail);
			stmt.setString(2, userPassword);
		    rs = stmt.executeQuery();
		   
			while (rs.next()) {
				
				LoggedInUserInfo userInfo = new LoggedInUserInfo();
				userInfo.setRole(rs.getString("roles"));
				userInfo.setUserEmail(rs.getString("userEmail"));
				System.out.println(rs.getString("userEmail"));
				userInfo.setUserFirstName(rs.getString("userFirstName"));
				userInfo.setUserMiddleName(rs.getString("userMiddleName"));
				userInfo.setUserLastName(rs.getString("userLastName"));
				userInfo.setUserProfilePhoto(rs.getString("userProfilePhoto"));
				userInfo.setUserID(rs.getInt("userID"));
				list.add(userInfo);
			}

		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}

		return list;	
	}
	
	
	public boolean checkUserEmailAlreadyExists(String email)
	{
		boolean isSuccess=false;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int count=0;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql="SELECT userID , userEmail from Customer where userEmail=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, email);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				count++;
			}
			if(count>0)
			{
				isSuccess=true;
			}
			
		}
		catch(SQLException e)
		{
		e.printStackTrace();	
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(isSuccess+"....loop");
		return isSuccess;
	}
	
	public boolean isUserRegistrationSuccess(String firstName , String middleName , String lastName , String email , String userPassword , String mobile)
	{
		boolean isSuccess = false;
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{
			String sql="exec hash_userDetails ? , ? , ? , ? , ? , ? , ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			cstmt=con.prepareCall(sql);
			cstmt.setString(1, firstName);
			cstmt.setString(2,middleName);
			cstmt.setString(3, lastName);
			cstmt.setString(4, email);
			cstmt.setString(5, userPassword);
			cstmt.setString(6, mobile);
			cstmt.setString(7, "customer");
			int rowEffected=cstmt.executeUpdate();
			if(rowEffected>0)
			{
				isSuccess = true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public List<LoggedInUserInfo> getUserInfo(int userID)
	{
		List<LoggedInUserInfo> userInfoList = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql="SELECT userFirstName, userProfilePhoto, userMiddleName, userLastName, userEmail , userPhone , userID FROM Customer "
					+ "WHERE "
					+ "userID = ?";
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			rs=stmt.executeQuery();
			while(rs.next()){
				
				LoggedInUserInfo info = new LoggedInUserInfo();
				info.setUserFirstName(rs.getString("userFirstName"));
				info.setUserMiddleName(rs.getString("userMiddleName"));
				info.setUserLastName(rs.getString("userLastName"));
				info.setUserID(rs.getInt("userID"));
				info.setUserEmail(rs.getString("userEmail"));
				info.setUserProfilePhoto(rs.getString("userProfilePhoto"));
				info.setUserPhone(rs.getString("userPhone"));
				userInfoList.add(info);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			try{
			rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		return userInfoList;
	}

	public boolean updateUserProfileInfo(int userID , String firstName , String middleName , String lastName , String email , String phone)
	{
		boolean check = true;
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE Customer "
					+ "SET "
					+ "userFirstName = ? , "
					+ "userMiddleName = ? , "
					+ "userLastName = ? , "
					+ "userEmail = ? , "
					+ "userPhone = ? "
					+ "WHERE "
					+ "userID = ? ";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, middleName);
			stmt.setString(3, lastName);
			stmt.setString(4, email);
			stmt.setString(5, phone);
			stmt.setInt(6, userID);
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			check = false;
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			check = false;
			e.printStackTrace();
		}catch(Exception e)
		{
			check = false;
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	
	}
		return check;
	}
	
	public boolean updateUserProfilePhoto(int userID , String path)
	{
		boolean check = true;
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE Customer "
					+ "SET "
					+ "userProfilePhoto = ? "
					+ "WHERE "
					+ "userID = ? ";
					
			stmt=con.prepareStatement(sql);
			stmt.setString(1, path);
			stmt.setInt(2, userID);
			
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			check = false;
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			check = false;
			e.printStackTrace();
		}catch(Exception e)
		{
			check = false;
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	
	}
		return check;
	}

}
