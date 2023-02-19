package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class DM_implementation_DB {
	    
	public static void main(String[] args) {
		
		DefaultTableModel model;
	    String url="jdbc:sqlite:" + System.getProperty("user.dir") + "/coursesdb";
		Connection connect;
		ResultSet result = null;
	    
		try{
			// Trying to establish connection with the database called "coursesdb" in url variable
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(url);
                  
            if(connect != null) // Checking the connection
                System.out.println("Conection Succesful!");
            
            PreparedStatement st = connect.prepareStatement("");
            result = st.executeQuery();
                        
        }catch(Exception x){
        	System.err.println(x.getMessage().toString());
        }
	}
	
	public boolean executeSQLcommand(String command, Connection connect) { // Function to execute table creation commands
		try {
			PreparedStatement st = connect.prepareStatement(command);
			boolean result = st.execute();
			if(result)
				return true;
			else
				return false;
		}catch(Exception x){
        	System.err.println(x.getMessage().toString());
        	return false;
        }
	}
	
	public ResultSet executeSQLquery(String command, Connection connect) { // Function to execute an SQL query
		try {
			ResultSet result;
			PreparedStatement st = connect.prepareStatement(command);
			result = st.executeQuery();
			if(result != null)
				return result;
			else
				return null;
		}catch(Exception x){
        	System.err.println(x.getMessage().toString());
        	return null;
        }
	}
	
}
