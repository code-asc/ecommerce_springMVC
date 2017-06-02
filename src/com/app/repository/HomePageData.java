package com.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.app.model.HomePageLargeImage;
import com.app.model.HomePageThumbNail;

@Repository
public class HomePageData 
{
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	final static Logger log = Logger.getLogger(HomePageData.class);
	
	public  List<HomePageLargeImage> homePageContent()
	{
		List<HomePageLargeImage> list =new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try
		{
			String sql="SELECT largePhoto from ProductPhoto where largePhotoName = ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			stmt=con.prepareStatement(sql);
			stmt.setString(1, "homepage");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				HomePageLargeImage largeImage=new HomePageLargeImage();
				largeImage.setLargePhoto(rs.getString("largePhoto").toString());
				list.add(largeImage);
			}
		}
		catch(SQLException e)
		{
			log.error("homePageContent argument method : "+ e.getMessage());
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			log.error("homePageContent argument method : "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try{
			rs.close();
			stmt.close();
			con.close();
			}
			catch(SQLException e)
			{
				log.error("homePageContent argument method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	public List<HomePageThumbNail> homePageContentThumbNail()
	{
		List<HomePageThumbNail> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try{
			
			String sql="SELECT thumbNailPhoto , brandID , subCategoryID from ProductPhoto where thumbNailPhotoName= ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url , userName , password);
			stmt=con.prepareStatement(sql);
			stmt.setString(1, "thumb");
			rs=stmt.executeQuery();
			while(rs.next()){
				HomePageThumbNail homePageThumNailData = new HomePageThumbNail();
				homePageThumNailData.setThumbNailPhoto(rs.getString("thumbNailPhoto").toString());
				homePageThumNailData.setBrandID(rs.getInt("brandID"));
				homePageThumNailData.setSubCategoryID(rs.getInt("subCategoryID"));
				list.add(homePageThumNailData);
				
			}
		}
		catch(SQLException e)
		{
			log.error("homePageContentThumbNail argument method : "+ e.getMessage());
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			log.error("homePageContentThumbNail argument method : "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}
			catch(SQLException e)
			{
				log.error("homePageContentThumbNail argument method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}
}
