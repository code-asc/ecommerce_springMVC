package com.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.CategoryType;
import com.app.model.SubCategoryType;


@Repository
public class AdminEdit {
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	
	public int addBrandToDatabase(String brandName)
	{
		int i = 1;
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql = "BEGIN "
					+ "IF NOT EXISTS(SELECT brandID FROM Brands "
					+ "WHERE  "
					+ "brandName = ? "
					+ ") "
					+ "BEGIN "
					+ "INSERT INTO Brands(brandName) "
					+ "VALUES( ? ) "
					+ "END "
					+ "ELSE "
					+ "THROW 50001 , 'row already Exists' , 198; "
					+ "END ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setString(1, brandName);
			stmt.setString(2, brandName);
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			i=0;
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			i=0;
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return i;
	}
	
	
	public int addCategoryToDatabase(String categoryType)
	{
		int i = 1;
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql = "BEGIN "
					+ "IF NOT EXISTS(SELECT categoryType FROM Category "
					+ "WHERE "
					+ "categoryType = ? "
					+ ") "
					+ "BEGIN "
					+ "INSERT INTO Category(categoryType) "
					+ "VALUES( ? ) "
					+ "END "
					+ "ELSE "
					+ "THROW 50001 , 'row already Exists' , 198; "
					+ "END ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setString(1, categoryType);
			stmt.setString(2, categoryType);
			stmt.executeUpdate();
			
		}catch(SQLException e)
		{
			i=0;
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			i=0;
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return i;
	}
	
	
	public List<CategoryType> getCategory()
	{
		List<CategoryType> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT categoryID , categoryType FROM Category ORDER BY categoryID DESC ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				CategoryType info = new CategoryType();
				info.setCategoryType(rs.getString("categoryType"));
				info.setCategoryID(rs.getInt("categoryID"));
				list.add(info);
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
	
	
	public List<SubCategoryType> getSubCategory()
	{
		List<SubCategoryType> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT subCategoryID , subCategoryType FROM SubCategory ORDER BY subCategoryID DESC ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				SubCategoryType info = new SubCategoryType();
				info.setSubCategoryType(rs.getString("subCategoryType"));
				info.setSubCategoryID(rs.getInt("subCategoryID"));
				list.add(info);
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
	
	
	public int addSubCategoryToDatabase(int categoryID , String subCategoryType)
	{
		int i = 1;
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql = "BEGIN "
					+ "IF NOT EXISTS(SELECT subCategoryID FROM SubCategory "
					+ "WHERE "
					+ "subCategoryType = ? "
					+ "AND "
					+ "categoryID = ? "
					+ ") "
					+ "BEGIN "
					+ "INSERT INTO SubCategory(categoryID,subCategoryType) "
					+ "VALUES( ? , ? ) "
					+ "END "
					+ "ELSE "
					+ "THROW 50001 , 'row already Exists' , 198; "
					+ "END ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setString(1, subCategoryType);
			stmt.setInt(2, categoryID);
			stmt.setInt(3, categoryID);
			stmt.setString(4, subCategoryType);
			stmt.executeUpdate();
			
		}catch(SQLException e)
		{
			i=0;
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			i=0;
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return i;
	}

}
