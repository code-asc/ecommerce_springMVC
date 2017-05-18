package com.app.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class UserCartDetails {
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	
	
	public int getDetailID(String status , int userID , int productID)
	{
		int detailID = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql="SELECT detailID FROM OrderDetails "
					+ "WHERE "
					+ "userID = ? "
					+ "AND "
					+ "detailProductID = ? "
					+ "AND "
					+ "status = ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, productID);
			stmt.setString(3, status);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				detailID=rs.getInt("detailID");
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
		return detailID;
	}
	
	public void setOrderDetails(String status , int userID , int productID , int supplierID , BigDecimal afterDiscount)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql="INSERT INTO OrderDetails(detailProductID , detailPrice , supplierID , status , userID , quantity) "
					+ "VALUES "
					+ "(?,?,?,?,?,?)";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, productID);
			stmt.setBigDecimal(2, afterDiscount);
			stmt.setInt(3, supplierID);
			stmt.setString(4, status);
			stmt.setInt(5, userID);
			stmt.setInt(6, 1);
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void updateOrderDetails(int userID , int productID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql="UPDATE OrderDetails "
					+ "SET quantity = quantity+1 "
					+ "WHERE "
					+ "detailProductID = ? "
					+ "AND "
					+ "userID = ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url,userName,password);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productID);
			stmt.setInt(2, userID);
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
