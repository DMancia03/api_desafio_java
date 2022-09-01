package sv.cuponera.dependientes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConnection {
	// JDBC driver name and database URL
		 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
		 static final String DB_URL = "jdbc:mysql://localhost/desafio_java";
		 // Database credentials
		 static final String USER = "root";
		 static final String PASS = "";
		 
		 Connection conn = null;
		 
		 Statement stmt = null;
		 PreparedStatement pstmt = null;
		 
		 ResultSet resultSet = null;
		 
		 public AppConnection() {
			 try {
				 Class.forName("com.mysql.jdbc.Driver"); 
			 }catch(Exception ex) {
				 Logger.getLogger(AppConnection.class.getName()).log(Level.SEVERE, null, ex);
			 }
		 }
		 public void connect()throws SQLException{
			 conn = DriverManager.getConnection(DB_URL, USER, PASS); 
		 }
		 public void close() throws SQLException{
			 if(conn != null) {
				 conn.close();
				 conn = null; 
			 }
		 }
}
