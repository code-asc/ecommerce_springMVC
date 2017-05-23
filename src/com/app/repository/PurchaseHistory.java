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

import com.app.model.PurchaseHistoryModel;

@Repository
public class PurchaseHistory {

	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	private String userName = "sa";
	private String password = "mindfire";
	
	public List<Integer> getPurchaseHistoryID(int userID , int start , int end)
	{
		List<Integer> idList = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql="WITH Result_CTE AS "
					+ "( "
					+ "SELECT Orders.orderID , ROW_NUMBER() "
					+ "OVER "
					+ "(order by Orders.orderID DESC) "
					+ "AS "
					+ "RowNum FROM Products "
					+ "INNER JOIN OrderDetails "
					+ "ON "
					+ "  OrderDetails.detailProductID=Products.productID "
					+ "INNER JOIN Orders "
					+ "ON "
					+ "Orders.orderID=OrderDetails.orderID "
					+ "WHERE "
					+ "Orders.userID = ? "
					+ "AND "
					+ "OrderDetails.displayStatus = ?"
					+ ") "
					+ "SELECT * FROM Result_CTE "
					+ "WHERE "
					+ "RowNum > ? "
					+ "AND "
					+ "RowNum <= ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setString(2, "available");
			stmt.setInt(3, start);
			stmt.setInt(4, end);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				idList.add(rs.getInt("orderID"));
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
		return idList;
	}
	
	public List<PurchaseHistoryModel> getPurchaseHistory(int userID , String detailID)
	{
		List<PurchaseHistoryModel> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql="SELECT Address.customerAddress1,Address.customerAddress2,Address.customerCity,orderDetails.detailID,Address.customerState,Address.customerCountry,Orders.orderID,Orders.orderAmount,format(Orders.orderDate,'dd/MM/yyyy') as orderDate,OrderDetails.detailPrice,Brands.brandName ,Products.productName,Products.afterDiscount , ProductPhoto.thumbNailPhoto , OrderDetails.status , OrderDetails.quantity FROM Products "
					+ "INNER JOIN ProductPhoto "
					+ "ON "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN OrderDetails "
					+ "ON "
					+ "OrderDetails.detailProductID=Products.productID "
					+ "INNER JOIN Orders "
					+ "ON "
					+ "Orders.orderID=OrderDetails.orderID "
					+ "INNER JOIN Brands "
					+ "ON "
					+ "Products.brandID=Brands.brandID "
					+ "INNER JOIN Address "
					+ "ON "
					+ "Address.userID=Orders.userID "
					+ "WHERE "
					+ "( "
					+ "Orders.userID = "+userID+" "
							+ "AND "
							+ "Address.addressType = 'default' "
							+ "AND "
							+ "Orders.addressID=Address.addressID "
							+ "AND "
							+ "Orders.orderID IN ("+detailID+") )"
									+ "OR "
									+ "( "
									+ "Orders.addressID=Address.addressID "
									+ "AND "
									+ "Orders.userID = "+userID+" ) "
											+ "AND "
											+ "Orders.orderID IN ("+detailID+") "
													+ "order by Orders.orderID DESC";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				PurchaseHistoryModel model = new PurchaseHistoryModel();
				model.setCustomerAddress1(rs.getString("customerAddress1"));
				model.setCustomerAddress2(rs.getString("customerAddress2"));
				model.setCustomerCity(rs.getString("customerCity"));
				model.setDetailID(rs.getInt("detailID"));
				model.setCustomerState(rs.getString("customerState"));
				model.setCustomerCountry(rs.getString("customerCountry"));
				model.setOrderID(rs.getInt("orderID"));
				model.setOrderAmount(rs.getBigDecimal("orderAmount"));
				model.setOrderDate(rs.getString("orderDate"));
				model.setDetailPrice(rs.getBigDecimal("detailPrice"));
				model.setBrandName(rs.getString("brandName"));
				model.setProductName(rs.getString("productName"));
				model.setAfterDiscount(rs.getBigDecimal("afterDiscount"));
				model.setThumbNailPhoto(rs.getString("thumbNailPhoto"));
				model.setStatus(rs.getString("status"));
				model.setQuantity(rs.getInt("quantity"));
				list.add(model);
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
	
	
	public List<PurchaseHistoryModel> getCustomerPurchaseHistory(int userID)
	{
		List<PurchaseHistoryModel> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql="SELECT Address.customerAddress1,Address.customerAddress2,Address.customerCity,orderDetails.detailID,Address.customerState,Address.customerCountry,Orders.orderID,Orders.orderAmount,format(Orders.orderDate,'dd/MM/yyyy') as orderDate,OrderDetails.detailPrice,Brands.brandName ,Products.productName,Products.afterDiscount , ProductPhoto.thumbNailPhoto , OrderDetails.status , OrderDetails.quantity FROM Products "
					+ "INNER JOIN ProductPhoto "
					+ "ON "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN OrderDetails "
					+ "ON "
					+ "OrderDetails.detailProductID=Products.productID "
					+ "INNER JOIN Orders "
					+ "ON "
					+ "Orders.orderID=OrderDetails.orderID "
					+ "INNER JOIN Brands "
					+ "ON "
					+ "Products.brandID=Brands.brandID "
					+ "INNER JOIN Address "
					+ "ON "
					+ "Address.userID=Orders.userID "
					+ "WHERE "
					+ "( "
					+ "Orders.userID = ? "
							+ "AND "
							+ "Address.addressType = ? "
							+ "AND "
							+ "Orders.addressID=Address.addressID "
							+ "AND "
							+ "OrderDetails.displayStatus = ?"
							+ ") "
									+ "OR "
									+ "( "
									+ "Orders.addressID = Address.addressID "
									+ "AND "
									+ "OrderDetails.displayStatus = ? "
									+ "AND "
									+ "Orders.userID = ? "
									+ ") "
									+ "order by Orders.orderID DESC";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setString(2, "default");
			stmt.setString(3, "available");
			stmt.setString(4, "available");
			stmt.setInt(5, userID);
			
			rs=stmt.executeQuery();
			while(rs.next())
			{
				PurchaseHistoryModel info = new PurchaseHistoryModel();
				info.setCustomerAddress1(rs.getString("customerAddress1"));
				info.setCustomerAddress2(rs.getString("customerAddress2"));
				info.setCustomerCity(rs.getString("customerCity"));
				info.setDetailID(rs.getInt("detailID"));
				info.setCustomerState(rs.getString("customerState"));
				info.setCustomerCountry(rs.getString("customerCountry"));
				info.setOrderID(rs.getInt("orderID"));
				info.setOrderAmount(rs.getBigDecimal("orderAmount"));
				info.setOrderDate(rs.getString("orderDate"));
				info.setDetailPrice(rs.getBigDecimal("detailPrice"));
				info.setBrandName(rs.getString("brandName"));
				info.setProductName(rs.getString("productName"));
				info.setAfterDiscount(rs.getBigDecimal("afterDiscount"));
				info.setThumbNailPhoto(rs.getString("thumbNailPhoto"));
				info.setStatus(rs.getString("status"));
				info.setQuantity(rs.getInt("quantity"));
				list.add(info);
			}
		}catch(SQLException e)
		{
			System.out.println("error handled here....1");

			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(Exception e){
				System.out.println("error handled here....");
				e.printStackTrace();
			}
		}
		return list;
	}
}
