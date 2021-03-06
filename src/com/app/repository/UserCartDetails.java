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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.app.model.CartDisplayList;
import com.app.model.CartProductInfo;
import com.app.model.OrderDetailsInfo;

@Repository
public class UserCartDetails {
	
	private String url = "jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;";
	
	private String userName = "sa";
	
	private String password = "mindfire";
	
	final static Logger log = Logger.getLogger(UserCartDetails.class);
	
	public int getDetailID(String status , int userID , int productID)
	{
		int detailID = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			
			String sql="SELECT detailID FROM OrderDetails "
					+ "WHERE "
					+ "userID = ? "
					+ "AND "
					+ "detailProductID = ? "
					+ "AND "
					+ "status = ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, productID);
			stmt.setString(3, status);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				detailID=rs.getInt("detailID");
			}
		}catch(SQLException e)
		{	
			log.error("getDetailID method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("getDetailID method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("getDetailID method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		return detailID;
	}
	
	public void setOrderDetails(String status , int userID , int productID , int supplierID , BigDecimal afterDiscount)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql="INSERT INTO OrderDetails(detailProductID , detailPrice , supplierID , status , userID , quantity) "
					+ "VALUES "
					+ "(?,?,?,?,?,?)";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,userName,password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, productID);
			stmt.setBigDecimal(2, afterDiscount);
			stmt.setInt(3, supplierID);
			stmt.setString(4, status);
			stmt.setInt(5, userID);
			stmt.setInt(6, 1);
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			log.error("setOrderDetails method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("setOrderDetails method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("setOrderDetails method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void updateOrderDetails(int userID , int productID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			
			String sql="UPDATE OrderDetails "
					+ "SET quantity = quantity+1 "
					+ "WHERE "
					+ "detailProductID = ? "
					+ "AND "
					+ "userID = ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url,userName,password);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productID);
			stmt.setInt(2, userID);
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			log.error("updateOrderDetails method : "+ e.getMessage());
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			log.error("updateOrderDetails method : "+ e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("updateOrderDetails method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public List<CartDisplayList> cartDisplayList(String status , int userID)
	{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<CartDisplayList> list = new ArrayList<>();
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sqlStatement = "SELECT Brands.brandName,OrderDetails.detailProductID,OrderDetails.quantity,Products.supplierID,OrderDetails.detailID,ProductPhoto.thumbNailPhoto,Products.afterDiscount,Products.productName,OrderDetails.supplierID,Supplier.supplierName,OrderDetails.status from OrderDetails "
					+ "INNER JOIN Products "
					+ "ON "
					+ "Products.productID=OrderDetails.detailProductID "
					+ "INNER JOIN ProductPhoto "
					+ "ON "
					+ "Products.photoID=ProductPhoto.photoID "
					+ "INNER JOIN Supplier "
					+ "ON "
					+ "Products.supplierID=Supplier.supplierID "
					+ "INNER JOIN Brands "
					+ "ON "
					+ "Products.brandID=Brands.brandID "
					+ "WHERE "
					+ "OrderDetails.userID = ? "
					+ "AND "
					+ "OrderDetails.status= ?";
		    stmt = con.prepareStatement(sqlStatement);
		    stmt.setInt(1, userID);
		    stmt.setString(2, status);
		    rs = stmt.executeQuery();
		   
			while (rs.next()){
				
				CartDisplayList cartInfo = new CartDisplayList();
					cartInfo.setBrandName(rs.getString("brandName"));
					cartInfo.setDetailProductID(rs.getInt("detailProductID"));
					cartInfo.setQuantity(rs.getInt("quantity"));
					cartInfo.setSupplierID(rs.getInt("supplierID"));
					cartInfo.setDetailID(rs.getInt("detailID"));
					cartInfo.setThumbNailPhoto(rs.getString("thumbNailPhoto"));
					cartInfo.setAfterDiscount(rs.getBigDecimal("afterDiscount"));
					cartInfo.setProductName(rs.getString("productName"));
					cartInfo.setSupplierName(rs.getString("supplierName"));
					cartInfo.setStatus(rs.getString("status"));
					
					list.add(cartInfo);
	                 }
           }catch(SQLException e)
			{
        	   log.error("cartDisplayList method : "+ e.getMessage());
        	   e.printStackTrace();
			}catch(ClassNotFoundException e){
				log.error("cartDisplayList method : "+ e.getMessage());
				e.printStackTrace();
			}finally{
				try{
					stmt.close();
					con.close();
				}catch(SQLException e){
					log.error("cartDisplayList method : "+ e.getMessage());
					e.printStackTrace();
				}
			}
		
		return list;
	}
	
	public void incrementQuantityInDatabase(int userID , int detailID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE OrderDetails "
					+ "SET quantity = quantity + 1 "
					+ "where "
					+ "userID = ? "
					+ "AND "
					+ "detailID = ? "
					+ "AND "
					+ "quantity <= ?"; 
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, userID);
	        stmt.setInt(2, detailID);
	        stmt.setInt(3, 10);
		    stmt.executeUpdate();
		
	}catch(SQLException e)
		{
			log.error("incrementQuantityInDatabase method : "+ e.getMessage());
			System.out.println(e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("incrementQuantityInDatabase method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("incrementQuantityInDatabase method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	
	}
	
	public void decrementQuantityInDatabase(int userID , int detailID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "UPDATE OrderDetails "
					+ "SET quantity = quantity - 1 "
					+ "where "
					+ "userID = ? "
					+ "AND "
					+ "detailID = ? "
					+ "AND "
					+ "quantity >= ?"; 
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, userID);
	        stmt.setInt(2, detailID);
	        stmt.setInt(3, 2);
		    stmt.executeUpdate();
		
	}catch(SQLException e)
		{
			log.error("decrementQuantityInDatabase method : "+ e.getMessage());
			System.out.println(e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("decrementQuantityInDatabase method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("decrementQuantityInDatabase method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	
	}

	public List<Integer> getOrderPriceAndQty(int userID)
	{
		List<Integer> list = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT sum(quantity) AS totalCount, sum(detailPrice*quantity) AS sum FROM OrderDetails "
					+ "WHERE "
					+ "userID = ? "
					+ "AND "
					+ "status = ? ";
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, userID);
	        stmt.setString(2, "addedToCart");
		    rs = stmt.executeQuery();
		    while(rs.next())
		    {		   
		    	list.add(rs.getInt("totalCount"));
		    	list.add(rs.getInt("sum"));
		    	
		    }
	}catch(SQLException e)
		{
			log.error("getOrderPriceAndQty method : "+ e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("getOrderPriceAndQty method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("getOrderPriceAndQty method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	
		return list;
	}
	
	public  List<OrderDetailsInfo> orderDetailsInfo(int detailID)
	{
		List<OrderDetailsInfo> list = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT detailPrice, quantity FROM OrderDetails "
					+ "WHERE "
					+ "detailID = ? ";
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, detailID);
		    rs = stmt.executeQuery();
		    while(rs.next())
		    {
		    	OrderDetailsInfo info = new OrderDetailsInfo();

		    	info.setDetailPrice(rs.getBigDecimal("detailPrice"));
		    	info.setQuantity(rs.getInt("quantity"));
		    	list.add(info);
		    }
	}catch(SQLException e)
		{
			log.error("orderDetailsInfo method : "+ e.getMessage());
			System.out.println(e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("orderDetailsInfo method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("orderDetailsInfo method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void deleteProductFromCart(int userID , int detailID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "DELETE FROM OrderDetails "
					+ "WHERE "
					+ "detailID = ? "
					+ "AND "
					+ "userID = ? ";
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, detailID);
	        stmt.setInt(2, userID);
		     stmt.executeUpdate();
		   
	}catch(SQLException e)
		{
			log.error("deleteProductFromCart method : "+ e.getMessage());
			System.out.println(e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("deleteProductFromCart method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("deleteProductFromCart method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public int updateCartCount(int userID)
	{
		int count = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "SELECT quantity from OrderDetails "
					+ "WHERE "
					+ "userID = ? "
					+ "AND "
					+ "status = ? ";
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, userID);
	        stmt.setString(2, "addedToCart");
		    rs=stmt.executeQuery();
		    
		    while(rs.next()){
		    	count = count + rs.getInt("quantity");
		    }
	}catch(SQLException e)
		{
			log.error("updateCartCount method : "+ e.getMessage());
			System.out.println(e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("updateCartCount method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error("updateCartCount method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
		
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("updateCartCount method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public long setOrder(int userID , long addressID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		long identityCol = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql = "INSERT INTO Orders(userID,addressID) "
					+ "VALUES(? , ?) ";
	        stmt = con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
	        stmt.setInt(1, userID);
	        stmt.setLong(2, addressID);
		    stmt.executeUpdate();
		    rs=stmt.getGeneratedKeys();
		    while(rs.next())
		    {
		    	identityCol = rs.getLong(1);
		    }
	}catch(SQLException e)
		{
			log.error("setOrder method : "+ e.getMessage());
			System.out.println(e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("setOrder method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error("setOrder method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
		
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("setOrder method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	return identityCol;
	}
	
	public void updateOrderDetailsBasedOnStatus(int userID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			String sql ="UPDATE OrderDetails "
					+ "SET OrderDetails.status='progress' "
					+ "FROM OrderDetails "
					+ "INNER JOIN Products "
					+ "ON "
					+ "Products.productID = OrderDetails.detailProductID "
					+ "WHERE "
					+ "OrderDetails.userID = ? "
					+ "AND "
					+ "OrderDetails.status = ? "
					+ "AND "
					+ "Products.unitInStock >= OrderDetails.quantity";
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, userID);
	        stmt.setString(2, "addedToCart");
		    stmt.executeUpdate();
		    
	}catch(SQLException e)
		{
			log.error("updateOrderDetailsBasedOnStatus method : "+ e.getMessage());
			System.out.println(e.getMessage());
 	   		e.printStackTrace();
		}catch(ClassNotFoundException e){
			log.error("updateOrderDetailsBasedOnStatus method : "+ e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error("updateOrderDetailsBasedOnStatus method : "+ e.getMessage());
			e.printStackTrace();
		}finally{
		
			try{
				stmt.close();
				con.close();
			}catch(SQLException e){
				log.error("setOrder method : "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public List<CartProductInfo> getCartProductDetails(int userID , long orderID , String status)
	{
		List<CartProductInfo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="SELECT detailProductID , quantity  FROM OrderDetails "
					+ "WHERE "
					+ "userID = ? "
					+ "AND "
					+ "status = ? "
					+ "AND "
					+ "orderID = ? ";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setString(2, status);
			stmt.setLong(3, orderID);
			rs=stmt.executeQuery();
			while(rs.next()){
				
				CartProductInfo info = new CartProductInfo();
				info.setProductID(rs.getInt("detailProductID"));
				info.setQuantity(rs.getInt("quantity"));
				list.add(info);
	                 }
           }catch(SQLException e)
			{
        	   log.error("getCartProductDetails method : "+ e.getMessage());
        	   e.printStackTrace();
			}catch(ClassNotFoundException e){
				log.error("getCartProductDetails method : "+ e.getMessage());
				e.printStackTrace();
			}finally{
				try{
					stmt.close();
					con.close();
				}catch(SQLException e){
					log.error("getCartProductDetails method : "+ e.getMessage());
					e.printStackTrace();
				}
			}
		
		return list;
	}
	
	
	@Async
	public void updateProductInCart(int productID , int quantity)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE Products "
					+ "SET unitInStock=unitInStock - ? "
					+ "WHERE "
					+ "productID = ?";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, quantity);
			stmt.setInt(2, productID);
			stmt.executeQuery();
           }catch(SQLException e)
			{
        	   log.error("updateProductInCart method : "+ e.getMessage());
        	   e.printStackTrace();
			}catch(ClassNotFoundException e){
				log.error("updateProductInCart method : "+ e.getMessage());
				e.printStackTrace();
			}finally{
				try{
					stmt.close();
					con.close();
				}catch(SQLException e){
					log.error("updateProductInCart method : "+ e.getMessage());
					e.printStackTrace();
				}
			}
		
	}
}