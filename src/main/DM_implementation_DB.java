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
	    
		try{
			// Trying to establish connection with the database called "coursesdb" in url variable
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(url);
                  
            if(connect != null)
                System.out.println("Conection Succesful!");
                        
        }catch(Exception x){
        	System.err.println(x.getMessage().toString());
                        
        }
	}
}
