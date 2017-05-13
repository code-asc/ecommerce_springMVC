package com.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.BrandsOnly;

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
			String sql="SELECT brandName from Brands";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				BrandsOnly brand = new BrandsOnly();
				brand.setBrandName(rs.getString("brandName").toString());
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
}
