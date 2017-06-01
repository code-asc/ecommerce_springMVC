package com.app.repository;

import java.math.BigDecimal;
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
import com.app.model.NotificationModel;
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
	
	
	public List<SubCategoryType> getSubCategory(int categoryID)
	{
		List<SubCategoryType> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "SELECT subCategoryID , subCategoryType FROM SubCategory "
					+ "where "
					+ "categoryID = ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1,categoryID);
			rs = stmt.executeQuery();
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




public void productEdit(int productID , String productDesc , BigDecimal unitPrice , BigDecimal discount , int unitInStock)
{
	Connection con = null;
	PreparedStatement stmt = null;
	try{
		
		String sql = "UPDATE Products "
				+ "SET productDesc = ? ,"
				+ "unitPrice = ? ,"
				+ "unitInStock = ? ,"
				+ "discount = ? "
				+ "WHERE "
				+ "productID = ?";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con=DriverManager.getConnection(url,userName,password);
		stmt=con.prepareStatement(sql);
		stmt.setString(1, productDesc);
		stmt.setBigDecimal(2, unitPrice);
		stmt.setInt(3, unitInStock);
		stmt.setBigDecimal(4, discount);
		stmt.setInt(5,productID);
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

public void productDelete(int productID )
{
	Connection con = null;
	PreparedStatement stmt = null;
	try{
		
		String sql = "UPDATE Products "
				+ "SET status='removed' "
				+ "WHERE "
				+ "productID = ?";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con=DriverManager.getConnection(url,userName,password);
		stmt=con.prepareStatement(sql);
		stmt.setInt(1,productID);
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

	public int editProductFromUserSinglePage(int productID , String productDesc , BigDecimal unitPrice , int unitInStock , BigDecimal discount , String thumbNailPhoto , String largePhoto)
	{
		int check = 1;
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement _stmt = null;
		try{
			
			String sqlForProduct = "UPDATE Products "
					+ "SET productDesc = ? ,"
					+ "unitPrice = ? ,"
					+ "unitInStock = ? ,"
					+ "discount = ? "
					+ "WHERE "
					+ "productID = ? ";
					
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			con.setAutoCommit(false);
			stmt=con.prepareStatement(sqlForProduct);
			stmt.setString(1, productDesc);
			stmt.setBigDecimal(2, unitPrice);
			stmt.setInt(3, unitInStock);
			stmt.setBigDecimal(4, discount);
			stmt.setInt(5, productID);
			stmt.executeUpdate();
			
			
			String sqlForPhoto = "UPDATE ProductPhoto "
					+ "SET thumbNailPhoto = ?  ,"
					+ "largePhoto = ? "
					+ "from ProductPhoto "
					+ "INNER JOIN Products "
					+ "ON "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "WHERE "
					+ "productID = ? ";
			_stmt = con.prepareStatement(sqlForPhoto);
			_stmt.setString(1, thumbNailPhoto);
			_stmt.setString(2, largePhoto);
			_stmt.setInt(3, productID);
			_stmt.executeUpdate();
			
			con.commit();
		}catch(SQLException e)
		{
			check = 0;
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			check = 0;
			e.printStackTrace();
		}catch(Exception e){
			check = 0;
			e.printStackTrace();
		}
			finally{
		
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			}
		return check;
	}
	
	public List<NotificationModel> notificationQuery()
	{
		List<NotificationModel> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql = "SELECT TOP 3 content , "
					+ "(SELECT count(case when markAs='unread' then 1 else null end) from Notification l "
					+ "where x.nid=l.nid) as totalRead , "
					+ "replace(convert(nvarchar,postTime,105),' ','/') as postTime "
					+ "FROM "
					+ "Notification x "
					+ "ORDER BY nid DESC ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				NotificationModel info = new NotificationModel();
				info.setContent(rs.getString("content"));
				info.setPostTime(rs.getString("postTime"));
				info.setCount(rs.getInt("totalRead"));
				list.add(info);
			}
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch(Exception e)
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
	
	
	public void insertNotificationDataQuery(String content)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try
		{
			String sql = "INSERT INTO Notification(content , postTime , markAs) "
					+ "VALUES(? , CURRENT_TIMESTAMP , ?)";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setString(2, "unread");
			stmt.executeUpdate();
			
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch(Exception e)
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

	public void markAsReadNotificationQuery()
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try
		{
			String sql = "UPDATE Notification "
					+ "SET "
					+ "markAs='read' "
					+ "WHERE "
					+ "nid IN (SELECT TOP 3 nid FROM Notification ORDER BY nid DESC) ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.executeUpdate();
			
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch(Exception e)
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
}
