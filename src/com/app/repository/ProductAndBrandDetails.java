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

import com.app.model.BrandsOnly;
import com.app.model.ProductDetails;

@Repository
public class ProductAndBrandDetails 
{
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	
	public List<BrandsOnly> brandOnly()
	{
		List<BrandsOnly> list = new ArrayList<>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try
		{
			String sql="SELECT brandName , brandID from Brands";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				BrandsOnly brand = new BrandsOnly();
				brand.setBrandName(rs.getString("brandName").toString());
				brand.setBrandID(rs.getInt("brandID"));
				list.add(brand);
				
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
		finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public List<BrandsOnly> getBrandBySubCategory(int subCategoryID)
	{
		List<BrandsOnly> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try{
			String sql="SELECT DISTINCT Brands.brandName,Brands.brandID from Products "
					+ "INNER JOIN ProductPhoto"
					+ " on "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Brands"
					+ " on "
					+ "Products.brandID=Brands.brandID"
					+ " where Products.subCategoryID=? "
					+ "ORDER BY Brands.brandName ASC";
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, subCategoryID);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				BrandsOnly brand = new BrandsOnly();
				brand.setBrandID(rs.getInt("brandID"));
				brand.setBrandName(rs.getString("brandName"));
				list.add(brand);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}finally{
		try{
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		}
		return list;
	}
	
	public List<ProductDetails> productDetailsUsingSubCategoryID(int subCategoryID)
	{
		List<ProductDetails> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try
		{
			String sql="SELECT Products.productID , Products.productName ,Products.productDesc ,Products.unitPrice ,ProductPhoto.thumbNailPhoto ,Products.discount ,Brands.brandName from Products "
					+ "INNER JOIN ProductPhoto "
					+ "on "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Brands "
					+ "on "
					+ "Products.brandID=Brands.brandID "
					+ "where "
					+ "Products.subCategoryID=? AND Products.status=?";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1,subCategoryID);
			stmt.setString(2,"available");
			rs=stmt.executeQuery();
			
			while(rs.next())
			{
				ProductDetails productDetails = new ProductDetails();
				productDetails.setProductID(rs.getInt("productID"));
				productDetails.setProductName(rs.getString("productName"));
				productDetails.setProductDesc(rs.getString("productDesc"));
				productDetails.setUnitPrice(rs.getBigDecimal("unitPrice"));
				productDetails.setThumbNailPhoto(rs.getString("thumbNailPhoto"));
				productDetails.setBrandName(rs.getString("brandName"));
				productDetails.setDiscount(rs.getBigDecimal("discount"));
				list.add(productDetails);
			}
	}
	catch(SQLException e){
		e.printStackTrace();
	}
    catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			}
		
		return list;
}
}
