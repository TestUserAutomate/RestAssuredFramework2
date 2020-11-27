package Utils;

import java.sql.*;

public class JdbcConnectivity {

	public Connection mysql_connectivity() throws Exception{
	                                           
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/nitin","root","root"); 
		return con;
		
	}
}
