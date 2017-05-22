package com.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.UserAddress;

@Repository
public class AddressDetails {
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	
	
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
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
}
