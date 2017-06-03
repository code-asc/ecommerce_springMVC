package com.app.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.app.model.LoggedInUserInfo;

@Repository
public class UserDetails {
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.userName}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	
	final static Logger log = Logger.getLogger(UserDetails.class);
	
	public List<LoggedInUserInfo> doLogin(String userEmail, String userPassword) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<LoggedInUserInfo> list = new ArrayList<>();
		System.out.println(userName);
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
				userInfo.setUserFirstName(rs.getString("userFirstName"));
				userInfo.setUserMiddleName(rs.getString("userMiddleName"));
				userInfo.setUserLastName(rs.getString("userLastName"));
				userInfo.setUserProfilePhoto(rs.getString("userProfilePhoto"));
				userInfo.setUserID(rs.getInt("userID"));
				list.add(userInfo);
			}

		}catch (SQLException e) {
			log.error("doLogin method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			log.error("doLogin method : "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					log.error("doLogin method : "+ e.getMessage());
					e.printStackTrace();
				}
			}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("doLogin method : "+ e.getMessage());
					e.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					log.error("doLogin method : "+ e.getMessage());
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
			log.error("checkUserEmailAlreadyExists method : "+ e.getMessage());
			e.printStackTrace();	
		}
		catch(ClassNotFoundException e)
		{
			log.error("checkUserEmailAlreadyExists method : "+ e.getMessage());
			e.printStackTrace();
		}
		
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
			log.error("isUserRegistrationSuccess method : "+ e.getMessage());
			e.printStackTrace();
			
		}
		catch(ClassNotFoundException e)
		{
			log.error("isUserRegistrationSuccess method : "+ e.getMessage());
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
			log.error("getUserInfo method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			log.error("getUserInfo method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			log.error("getUserInfo method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			rs.close();
			}catch(Exception e)
			{
				log.error("getUserInfo method : "+ e.getMessage());
				e.printStackTrace();
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					log.error("getUserInfo method : "+ e.getMessage());
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
			log.error("updateUserProfileInfo method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			check = false;
			log.error("updateUserProfileInfo method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			check = false;
			log.error("updateUserProfileInfo method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("updateUserProfileInfo method : "+ e.getMessage());
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
			log.error("updateUserProfilePhoto method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			check = false;
			log.error("updateUserProfilePhoto method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			check = false;
			log.error("updateUserProfilePhoto method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("updateUserProfilePhoto method : "+ e.getMessage());
				e.printStackTrace();
			}
	
	}
		return check;
	}

	public boolean checkUserOnline(int userID)
	{
		boolean check = false;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT OnlineUser.userID,OnlineUser.email FROM OnlineUser "
					+ "WHERE "
					+ "userID = ?";
					
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				check = true;
			}
			
		}catch (SQLException e) {
			log.error("checkUserOnline method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			log.error("checkUserOnline method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			log.error("checkUserOnline method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("checkUserOnline method : "+ e.getMessage());
				e.printStackTrace();
			}
	
	}
		return check;
	}
	
	
	public void updateUserToOffline(int userID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE OnlineUser "
					+ "SET status='offline' "
					+ "WHERE "
					+ "userID = ?";
					
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.executeUpdate();
			
			
		}catch (SQLException e) {
			log.error("updateUserToOffline method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			log.error("updateUserToOffline method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			log.error("updateUserToOffline method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("updateUserToOffline method : "+ e.getMessage());
				e.printStackTrace();
			}
	
	}

	}
	
	public void updateUserToOnline(int userID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE OnlineUser "
					+ "SET status='online' "
					+ "WHERE "
					+ "userID = ?";
					
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.executeUpdate();
			
			
		}catch (SQLException e) {
			log.error("updateUserToOnline method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			
			log.error("updateUserToOnline method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			log.error("updateUserToOnline method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("updateUserToOnline method : "+ e.getMessage());
				e.printStackTrace();
			}
	
	}

	}
	

	
	public void changeUserStatusOnline(int userID , String userEmail)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO OnlineUser(userID , status , email) "
					+ "VALUES(? , ? , ?) ";
				
					
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setString(2, "online");
			stmt.setString(3, userEmail);
			stmt.executeUpdate();
			
			
		}catch (SQLException e) {
			log.error("changeUserStatusOnline method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			log.error("changeUserStatusOnline method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			log.error("changeUserStatusOnline method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("changeUserStatusOnline method : "+ e.getMessage());
				e.printStackTrace();
			}
	
	}

	}
	
	
	public void removeUserStatus(int userID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "DELETE FROM onlineUser "
					+ "WHERE "
					+ "userID = ?";
				
					
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.executeUpdate();
			
			
		}catch (SQLException e) {
			log.error("removeUserStatus method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			log.error("removeUserStatus method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			log.error("removeUserStatus method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("removeUserStatus method : "+ e.getMessage());
				e.printStackTrace();
			}
	
	}

	}
	
	public int countOnlineUsers()
	{
		int count = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT count(userID) AS totalUsers FROM OnlineUser "
					+ "WHERE "
					+ "status = ? ";
				
					
			stmt=con.prepareStatement(sql);
			stmt.setString(1, "online");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				count = rs.getInt("totalUsers");
			}
		
		}catch (SQLException e) {
			log.error("countOnlineUsers method : "+ e.getMessage());
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}catch (ClassNotFoundException e){
			log.error("countOnlineUsers method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e)
		{
			log.error("countOnlineUsers method : "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			try{
			rs.close();
			con.close();
			stmt.close();
			}catch(Exception e){
				log.error("countOnlineUsers method : "+ e.getMessage());
				e.printStackTrace();
			}
	
	}
		
		return count;
	}

	
}
