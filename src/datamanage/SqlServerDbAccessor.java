package datamanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//Adrian Faircloth
//12-6-22
//CSC480 Test 2
//Class for connecting to database and getting results from query
//Obtained from CSC480 Workshop 6
public class SqlServerDbAccessor {

	private Connection con;
	private Statement stmt;
	private PreparedStatement prepStmt;
	private ResultSet rs;
	
	private String connectionUrl;
	
	private String defaultConnUrl = "jdbc:sqlserver://;" +
            "servername=csdata.cd4sevot432y.us-east-1.rds.amazonaws.com;"
			+ "user=csc312cloud;password=c3s!c2Cld;";
	
	/**
	 * Constructor for SqlServerDbAccessor, sets connectionUrl to default value
	 */
	public SqlServerDbAccessor() {
		connectionUrl = defaultConnUrl;
	}
	
	/**
	 * Constructor for SqlServerDbAccessor, sets connectionUrl with values passed in
	 * @param serverName the name of the SQL server
	 * @param user the username for the server
	 * @param pwd the password for the server
	 * @param dbName the name of the database to query
	 */
	public SqlServerDbAccessor(String serverName, String user, String pwd, 
			String dbName) {
		connectionUrl = "jdbc:sqlserver://;";
		connectionUrl += "servername=" + serverName + ";"; 
		connectionUrl += "user=" + user + ";"; 
		connectionUrl += "password=" + pwd + ";"; 
		connectionUrl += "databaseName=" + dbName + ";"; 
	}
	
	/**
	 * Setter for setting the name of the database to query
	 * @param dbName the name of the database to query
	 */
	public void setDbName(String dbName) {
		connectionUrl += "databaseName=" + dbName;
	}
	
	/**
	 * Method for connecting to the specified database
	 */
	public void connectToDb() {
    	try {
    		// Establish the connection.
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	con = DriverManager.getConnection(connectionUrl);
    	} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * getter for PreparedStatement
	 * @return prepStmt the PreparedStatement
	 */
	public PreparedStatement getPrepStmt() {
		// TODO Auto-generated method stub
		return prepStmt;
	}

	/**
	 * Getter for the database connection
	 * @return con the database connection
	 */
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return con;
	}

	/**
	 * Getter for database connection URL
	 * @return connectionUrl the database connection URL
	 */
	public String getUrl() {
		// TODO Auto-generated method stub
		return connectionUrl;
	}
}
