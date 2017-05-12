import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShopping";
		String userName="sa";
		String password="mindfire";
		try{
			
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con=DriverManager.getConnection(url, userName, password);
		String l="select userID , userFirstName , userLastName , userMiddleName from Customer where userEmail=? AND userPassword=hashbytes('sha2_512',convert(varchar, ?))";		
		PreparedStatement stmt=con.prepareStatement(l);
		stmt.setString(1,"avgchowdary@gmail.com");
		stmt.setString(2,"hey123");
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getString("userFirstName"));
		}
		System.out.println("done");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
