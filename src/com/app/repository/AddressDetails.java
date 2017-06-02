package com.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.app.model.UserAddress;

@Repository
public class AddressDetails {
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	final static Logger log = Logger.getLogger(AddressDetails.class);
	
	public List<UserAddress> userAddressDetails(int userID , String type)
	{
		List<UserAddress> list =new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql="SELECT Address.addressID,Address.customerAddress1,Address.customerAddress2,Address.customerCity,Address.customerState,Address.customerCountry,Address.addressType "
					+ "FROM "
					+ "Address "
					+ "WHERE "
					+ "userID = ? "
					+ "AND "
					+ "addressType = ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setString(2, type);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				UserAddress userAddress = new UserAddress();
				userAddress.setAddressID(rs.getInt("addressID"));
				userAddress.setAddressType(rs.getString("addressType"));
				userAddress.setCustomerAddress1(rs.getString("customerAddress1"));
				userAddress.setCustomerAddress2(rs.getString("customerAddress2"));
				userAddress.setCustomerCity(rs.getString("customerCity"));
				userAddress.setCustomerCountry(rs.getString("customerCountry"));
				userAddress.setCustomerState(rs.getString("customerState"));
				list.add(userAddress);
			}
		}catch(SQLException e)
		{
			log.error("userAddressDetails method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("userAddressDetails method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("userAddressDetails method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void updateAddressStatusToNull(int userID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql = "UPDATE Address "
					+ "SET addressType=NULL "
					+ "WHERE "
					+ "userID = ? "
					+ "AND "
					+ "addressType = ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setString(2, "default");
			stmt.executeUpdate();
			
		}catch(SQLException e)
		{
			log.error("updateAddressStatusToNull method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("updateAddressStatusToNull method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("updateAddressStatusToNull method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public long setNewDefaultAddress(int userID , String country , String state , String city , String address , String address2 , String zipCode)
	{
		long identityCol = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "INSERT INTO Address(customerAddress1 , customerAddress2 , customerZip , customerCity , customerState , customerCountry , userID , addressType) "
					+ "VALUES(? , ? , ? , ? , ? , ? , ? , ?) ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, address);
			stmt.setString(2, address2);
			stmt.setString(3, zipCode);
			stmt.setString(4, city);
			stmt.setString(5, state);
			stmt.setString(6, country);
			stmt.setInt(7, userID);
			stmt.setString(8, "default");
			stmt.executeUpdate();
			rs=stmt.getGeneratedKeys();
			while(rs.next())
			{
				identityCol = rs.getLong(1);
			}
			
		}catch(SQLException e)
		{   
			System.out.println("error in setNewDefaultAddress");
		    log.error("setNewDefaultAddress method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("setNewDefaultAddress method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return identityCol;
	}
	
	public long setTempAddress(int userID , String country , String state , String city , String address , String address2 , String zipCode)
	{
		long identityCol = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{

			String sql = "INSERT INTO Address(customerAddress1 , customerAddress2 , customerZip , customerCity , customerState , customerCountry , userID ) "
					+ "VALUES(? , ? , ? , ? , ? , ? , ?) ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, address);
			stmt.setString(2, address2);
			stmt.setString(3, zipCode);
			stmt.setString(4, city);
			stmt.setString(5, state);
			stmt.setString(6, country);
			stmt.setInt(7, userID);
			stmt.executeUpdate();
			rs=stmt.getGeneratedKeys();
			while(rs.next())
			{
				identityCol = rs.getLong(1);
			}
			
		}catch(SQLException e)
		{
			System.out.println("error in setTempAddress");
			log.error("setTempAddress method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("setTempAddress method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				
				log.error("setTempAddress method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		return identityCol;
	}
}
