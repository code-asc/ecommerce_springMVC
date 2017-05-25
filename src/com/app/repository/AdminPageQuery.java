package com.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.AdminCategoryCount;
import com.app.model.AdminCountryCount;
import com.app.model.AdminCustomerCount;
import com.app.model.AdminProductCount;
import com.app.model.AdminShippingCount;
import com.app.model.AdminSubCategoryCount;
import com.app.model.AdminSupplierCount;

@Repository
public class AdminPageQuery {
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	
	public List<AdminCountryCount> countCountry()
	{
		List<AdminCountryCount> list =new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT count(customerCountry) AS total , customerCountry FROM Address "
					+ "GROUP BY "
					+ "customerCountry";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminCountryCount details = new AdminCountryCount();
				details.setCustomerCountry(rs.getString("customerCountry"));
				details.setTotal(rs.getInt("total"));
				list.add(details);
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
	
	
	
	public List<AdminProductCount> countProduct()
	{
		List<AdminProductCount> list =new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT count(productID) as total FROM Products ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminProductCount details = new AdminProductCount();
				details.setTotal(rs.getInt("total"));
				list.add(details);
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

	public List<AdminCustomerCount> countCustomer()
	{
		List<AdminCustomerCount> list =new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT count(userID) AS total FROM Customer ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminCustomerCount details = new AdminCustomerCount();
				details.setTotal(rs.getInt("total"));
				list.add(details);
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
	
	public List<AdminCategoryCount> countCategory()
	{
		List<AdminCategoryCount> list =new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT count(categoryID) AS total FROM Category ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminCategoryCount details = new AdminCategoryCount();
				details.setTotal(rs.getInt("total"));
				list.add(details);
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
	
	public List<AdminSubCategoryCount> countSubCategory()
	{
		List<AdminSubCategoryCount> list =new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT count(subCategoryID) as total FROM SubCategory ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminSubCategoryCount details = new AdminSubCategoryCount();
				details.setTotal(rs.getInt("total"));
				list.add(details);
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
	
	public List<AdminSupplierCount> countSupplier()
	{
		List<AdminSupplierCount> list =new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT count(supplierID) AS total FROM Supplier ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminSupplierCount details = new AdminSupplierCount();
				details.setTotal(rs.getInt("total"));
				list.add(details);
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
	
	public List<AdminShippingCount> countShipping()
	{
		List<AdminShippingCount> list =new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT count(shippingID) as total FROM ShippingDetails ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminShippingCount details = new AdminShippingCount();
				details.setTotal(rs.getInt("total"));
				list.add(details);
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
