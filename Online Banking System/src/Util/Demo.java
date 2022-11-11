package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo {
	public static Connection GetUtilConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url="jdbc:mysql://localhost:3306/banking";
		try{
			conn=DriverManager.getConnection(url,"root","1414");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}

}
