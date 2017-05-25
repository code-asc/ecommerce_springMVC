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
import com.app.model.HighestSoldItem;
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
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
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
	
	
	public List<BrandsOnly> getBrandBySubCategory(int subCategoryID)
	{
		List<BrandsOnly> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
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
			con = DriverManager.getConnection(url,userName,password);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, subCategoryID);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				BrandsOnly brand = new BrandsOnly();
				brand.setBrandID(rs.getInt("brandID"));
				brand.setBrandName(rs.getString("brandName"));
				list.add(brand);
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
	
	public List<ProductDetails> productDetailsUsingSubCategoryID(int subCategoryID)
	{
		List<ProductDetails> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
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
			con = DriverManager.getConnection(url,userName,password);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,subCategoryID);
			stmt.setString(2,"available");
			rs = stmt.executeQuery();
			
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
	
	public List<ProductDetails> productDetailsUsingProductID(int productID)
	{
		List<ProductDetails> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql="SELECT  Products.productID , Products.productName ,Products.productDesc ,Products.unitPrice,Products.unitInStock,ProductPhoto.photoID ,ProductPhoto.largePhoto,ProductPhoto.thumbNailPhoto ,Products.discount,Products.supplierID,Products.afterDiscount ,Brands.brandName ,SubCategory.subCategoryType,Category.categoryType,SubCategory.subCategoryID,Category.categoryID from Products "
					+ "INNER JOIN ProductPhoto "
					+ "ON "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Brands "
					+ "ON "
					+ "Products.brandID=Brands.brandID "
					+ "INNER JOIN SubCategory "
					+ "ON "
					+ "Products.subCategoryID=SubCategory.subCategoryID "
					+ "INNER JOIN Category "
					+ "ON "
					+ "Subcategory.categoryID=Category.categoryID "
					+ "WHERE "
					+ "Products.productID=? "
					+ "AND "
					+ "Products.status=? ";
		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url , userName , password);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productID);
			stmt.setString(2, "available");
			rs = stmt.executeQuery();
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
				productDetails.setPhotoID(rs.getInt("photoID"));
				productDetails.setUnitInStock(rs.getInt("unitInStock"));
				productDetails.setLargePhoto(rs.getString("largePhoto"));
				productDetails.setSupplierID(rs.getInt("supplierID"));
				productDetails.setAfterDiscount(rs.getBigDecimal("afterDiscount"));
				productDetails.setSubCategoryType(rs.getString("subCategoryType"));
				productDetails.setCategoryType(rs.getString("categoryType"));
				productDetails.setSubCategoryID(rs.getInt("subCategoryID"));
				productDetails.setCategoryID(rs.getInt("categoryID"));
				list.add(productDetails);
			}
		}
		catch(SQLException e)
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
	
	public List<ProductDetails> filteredProducts(ArrayList<String> brandID , ArrayList<String> discount , int subCategoryID)
	{
		List<ProductDetails> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql = "SELECT Products.productID , Products.productName ,Products.productDesc ,Products.unitPrice ,ProductPhoto.thumbNailPhoto ,Products.discount ,Brands.brandName from Products "
					+ "INNER JOIN ProductPhoto "
					+ "on "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Brands "
					+ "on "
					+ "Products.brandID=Brands.brandID "
					+ "where "
					+ "Products.subCategoryID="+subCategoryID+" AND Products.status='available' ";
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url , userName , password);
			stmt = con.createStatement();
			
			if(brandID != null && brandID.size()>0)
			{
				StringBuilder sqlExtention = new StringBuilder();
				for(int index=0 ; index < brandID.size() ; index++)
				{
					sqlExtention.append(brandID.get(index));
					if(index != brandID.size()-1)
					{
						sqlExtention.append(" , ");
					}
				}
				sql = sql.concat(" AND Brands.brandID IN ("+sqlExtention+")");
			}
			
			if(discount != null && discount.size()>0)
			{
				StringBuilder sqlExtension = new StringBuilder();
				for(int index=0 ; index < discount.size() ; index++)
				{
					sqlExtension.append(discount.get(index));
					if(index != discount.size()-1)
					{
						sqlExtension.append(" , ");
					}
				}
				sql = sql.concat(" AND Products.discount IN ("+sqlExtension+")");

				
			}
			
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
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
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
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
	
	public List<ProductDetails> productInfoForSearchPage()
	{
		List<ProductDetails> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT Products.productID , Products.productName , Products.subCategoryID ,Products.productDesc ,Products.unitPrice ,ProductPhoto.thumbNailPhoto ,Products.discount ,Brands.brandName from Products "
					+ "INNER JOIN ProductPhoto "
					+ "on "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Brands "
					+ "on "
					+ "Products.brandID=Brands.brandID "
					+ "INNER JOIN SubCategory "
					+ "ON "
					+ "Products.subCategoryID=SubCategory.subCategoryID "
					+ "INNER JOIN Category "
					+ "ON "
					+ "Subcategory.categoryID=Category.categoryID "
					+ "where "
					+ "Products.status='available' ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url , userName , password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
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
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
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
	
	public List<ProductDetails> productInfoForSearchPage(String brand)
	{
		List<ProductDetails> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT Products.productID , Products.productName , Products.subCategoryID ,Products.productDesc ,Products.unitPrice ,ProductPhoto.thumbNailPhoto ,Products.discount ,Brands.brandName from Products "
					+ "INNER JOIN ProductPhoto "
					+ "on "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Brands "
					+ "on "
					+ "Products.brandID=Brands.brandID "
					+ "INNER JOIN SubCategory "
					+ "ON "
					+ "Products.subCategoryID=SubCategory.subCategoryID "
					+ "INNER JOIN Category "
					+ "ON "
					+ "Subcategory.categoryID=Category.categoryID "
					+ "where "
					+ "Products.status='available' "
					+ "AND "
					+ "Brands.brandName LIKE ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url , userName , password);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%"+brand+"%");
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
				productDetails.setSubCategoryID(rs.getInt("subCategoryID"));
				list.add(productDetails);
			}
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
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
	
	public List<ProductDetails> similarProducts(int productID , int subCategoryID)
	{
		List<ProductDetails> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT TOP 3 Products.productID , Products.productName , Products.subCategoryID ,Products.productDesc ,Products.unitPrice ,ProductPhoto.thumbNailPhoto ,Products.discount ,Brands.brandName from Products "
					+ "INNER JOIN ProductPhoto "
					+ "on "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Brands "
					+ "on "
					+ "Products.brandID=Brands.brandID "
					+ "INNER JOIN SubCategory "
					+ "ON "
					+ "Products.subCategoryID=SubCategory.subCategoryID "
					+ "INNER JOIN Category "
					+ "ON "
					+ "Subcategory.categoryID=Category.categoryID "
					+ "where "
					+ "Products.status = ? "
					+ "AND "
					+ "Products.subCategoryID = ? "
					+ "AND "
					+ "NOT Products.productID = ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url , userName , password);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "available");
			stmt.setInt(2, subCategoryID);
			stmt.setInt(3,productID);
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
				productDetails.setSubCategoryID(rs.getInt("subCategoryID"));
				list.add(productDetails);
			}
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
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
	
	public void updateProductQtyOnOrder(int productID) 
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql = "UPDATE Products "
					+ "SET unitInStock = unitInStock-1 "
					+ "WHERE "
					+ "productID = ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url , userName , password);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,productID);
			stmt.executeUpdate();
			}catch(SQLException e)
		{
			System.out.println(e.getMessage());
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
	
	public List<HighestSoldItem> highestSoldProduct()
	{
		List<HighestSoldItem> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT sum(OrderDetails.quantity) AS total ,Brands.brandName  from OrderDetails "
					+ "INNER JOIN Products "
					+ "ON "
					+ "Products.productID=OrderDetails.detailProductID "
					+ "INNER JOIN Brands "
					+ "ON "
					+ "Products.brandID=Brands.brandID "
					+ "group by Brands.brandName "; 
		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url , userName , password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				HighestSoldItem details = new HighestSoldItem();
				details.setBrandName(rs.getString("brandName"));
				details.setTotal(rs.getInt("total"));
				list.add(details);
			}
	
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
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

