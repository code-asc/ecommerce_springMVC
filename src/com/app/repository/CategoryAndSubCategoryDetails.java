package com.app.repository;

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

import com.app.model.HeaderSubcategoryDetails;


@Repository
public class CategoryAndSubCategoryDetails {
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	
	private String userName = "sa";
	
	private String password = "mindfire";
	
	final static Logger log = Logger.getLogger(CategoryAndSubCategoryDetails.class);
	
	public List<HeaderSubcategoryDetails> subCategoryDetails(int categoryID_1)
	{
		List<HeaderSubcategoryDetails> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try
		{
			String sql="SELECT Category.categoryType , SubCategory.subCategoryType , SubCategory.subCategoryID from Category "
					+ "INNER JOIN SubCategory "
					+ "on "
					+ "Category.categoryID=SubCategory.categoryID "
					+ "where "
					+ "Category.categoryID IN (?)";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, categoryID_1);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				HeaderSubcategoryDetails details = new HeaderSubcategoryDetails();
				details.setCategoryType(rs.getString("categoryType"));
				details.setSubCategoryType(rs.getString("subCategoryType"));
				details.setSubCategoryID(rs.getInt("subCategoryID"));
				list.add(details);
			}
		}
		catch(SQLException e)
		{
			log.error("subCategoryDetails argument method : "+ e.getMessage());
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			log.error("subCategoryDetails argument method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("subCategoryDetails argument method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	
	public List<HeaderSubcategoryDetails> subCategoryDetails(int categoryID_1 , int categoryID_2)
	{
		List<HeaderSubcategoryDetails> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try
		{
			String sql="SELECT Category.categoryType , SubCategory.subCategoryType , SubCategory.subCategoryID from Category "
					+ "INNER JOIN SubCategory "
					+ "on "
					+ "Category.categoryID=SubCategory.categoryID "
					+ "where "
					+ "Category.categoryID IN (? , ?)";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, categoryID_1);
			stmt.setInt(2, categoryID_2);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				HeaderSubcategoryDetails details = new HeaderSubcategoryDetails();
				details.setCategoryType(rs.getString("categoryType"));
				details.setSubCategoryType(rs.getString("subCategoryType"));
				details.setSubCategoryID(rs.getInt("subCategoryID"));
				list.add(details);
			}
		}
		catch(SQLException e)
		{
			log.error("subCategoryDetails argument method : "+ e.getMessage());
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			log.error("subCategoryDetails argument method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("subCategoryDetails argument method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<HeaderSubcategoryDetails> subCategoryDetails(int categoryID_1 , int categoryID_2 , int categoryID_3)
	{
		List<HeaderSubcategoryDetails> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try
		{
			String sql="SELECT Category.categoryType , SubCategory.subCategoryType , SubCategory.subCategoryID from Category "
					+ "INNER JOIN SubCategory "
					+ "on "
					+ "Category.categoryID=SubCategory.categoryID "
					+ "where "
					+ "Category.categoryID IN (? , ? , ?)";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, categoryID_1);
			stmt.setInt(2, categoryID_2);
			stmt.setInt(3, categoryID_3);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				HeaderSubcategoryDetails details = new HeaderSubcategoryDetails();
				details.setCategoryType(rs.getString("categoryType"));
				details.setSubCategoryType(rs.getString("subCategoryType"));
				details.setSubCategoryID(rs.getInt("subCategoryID"));
				list.add(details);
			}
		}
		catch(SQLException e)
		{
			log.error("subCategoryDetails argument method : "+ e.getMessage());
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			log.error("subCategoryDetails argument method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("subCategoryDetails argument method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		return list;
	}

}
