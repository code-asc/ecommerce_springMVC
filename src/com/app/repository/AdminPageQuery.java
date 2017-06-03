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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.userName}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	
	final static Logger log = Logger.getLogger(AdminPageQuery.class);
	
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
			log.error("countCountry  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("countCountry  method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("countCountry  method : "+ e.getMessage());
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
			log.error("countProduct  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("countProduct  method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("countProduct  method : "+ e.getMessage());
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
			log.error("countCustomer  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("countCustomer  method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("countCustomer  method : "+ e.getMessage());
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
			log.error("countCategory  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("countCategory  method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("countCategory  method : "+ e.getMessage());
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
			log.error("countSubCategory  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("countSubCategory  method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("countSubCategory  method : "+ e.getMessage());
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
			
			String sql = "SELECT count(supplierID) AS total , supplierName , supplierID FROM Supplier "
					+ "group by  supplierName , supplierID";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminSupplierCount details = new AdminSupplierCount();
				details.setTotal(rs.getInt("total"));
				details.setSupplierID(rs.getInt("supplierID"));
				details.setSupplierName(rs.getString("supplierName"));
				list.add(details);
			}
		}catch(SQLException e)
		{
			log.error("countSupplier  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("countSupplier  method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("countSupplier  method : "+ e.getMessage());
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
			
			String sql = "SELECT count(shippingID) as total  , shippingID , companyName FROM ShippingDetails "
					+ " group by shippingID , companyName ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				AdminShippingCount details = new AdminShippingCount();
				details.setTotal(rs.getInt("total"));
				details.setCompanyName(rs.getString("companyName"));
				details.setShippingID(rs.getInt("shippingID"));
				list.add(details);
			}
		}catch(SQLException e)
		{
			log.error("countShipping  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("countShipping  method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("countShipping  method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		return list;

	}
	
	public int addNewProduct(String productName , String productDesc , int supplierID , int subcategoryID , BigDecimal unitPrice , String thumbNail , String thumbNailType , String largePhotoType , String largePhoto , int quantity , BigDecimal discount , int rating , int brandID)
	{
		int check = 1;
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement _stmt = null;
		ResultSet rs = null;
		try{
			long photoID = 0;
			String sqlForPhoto = "INSERT INTO ProductPhoto(thumbNailPhoto , thumbNailPhotoName , largePhoto , largePhotoName , brandID , subCategoryID) "
					+ "VALUES(? , ? , ? , ? , ? , ?)";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			con.setAutoCommit(false);
			stmt=con.prepareStatement(sqlForPhoto , Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, thumbNail);
			stmt.setString(2, thumbNailType);
			stmt.setString(3, largePhoto);
			stmt.setString(4, largePhotoType);
			stmt.setInt(5, brandID);
			stmt.setInt(6, subcategoryID);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			while(rs.next())
			{
				photoID = rs.getLong(1);
			}
			
			String sqlForProduct = "BEGIN "
					+ "IF NOT EXISTS(SELECT productID FROM Products "
					+ "WHERE "
					+ "productName = ? )"
					+ "BEGIN "
					+ "INSERT INTO Products(productName , productDesc , supplierID , subcategoryID , unitPrice, photoID , unitInStock , discount , rating , brandID , status) "
					+ "VALUES(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?) "
					+ "END "
					+ "ELSE "
					+ "THROW 50001 , 'row already Exists' ,198; "
					+ "END ";
			_stmt = con.prepareStatement(sqlForProduct);
			_stmt.setString(1, productName);
			_stmt.setString(2, productName);
			_stmt.setString(3, productDesc);
			_stmt.setInt(4, supplierID);
			_stmt.setInt(5, subcategoryID);
			_stmt.setBigDecimal(6, unitPrice);
			_stmt.setLong(7, photoID);
			_stmt.setInt(8, quantity);
			_stmt.setBigDecimal(9, discount);
			_stmt.setInt(10, rating);
			_stmt.setInt(11, brandID);
			_stmt.setString(12, "available");
			_stmt.executeUpdate();
			
			con.commit();
		}catch(SQLException e)
		{
			check = 0;
			log.error("addNewProduct  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			check = 0;
			log.error("addNewProduct  method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			check = 0;
			log.error("addNewProduct  method : "+ e.getMessage());
			e.printStackTrace();
		}
			finally{
		
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("addNewProduct  method : "+ e.getMessage());
				e.printStackTrace();
			}
			}
		return check;

	}
}


