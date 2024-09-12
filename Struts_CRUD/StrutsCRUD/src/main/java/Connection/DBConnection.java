		package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {

	private static Connection con = null;
	private static final Logger logger = LogManager.getLogger(DBConnection.class);
	public Connection getConnection(){
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_DB", "root", "root");
			logger.info("Database Connection Successful....");
			
		}catch(Exception e){
			logger.error("Database Connection Failed");
			e.printStackTrace();
		}
		
		return con;
	}
	
}
