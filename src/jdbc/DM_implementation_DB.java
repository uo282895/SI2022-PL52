package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DM_implementation_DB {
	    
	public static void main(String[] args) {
		
		DefaultTableModel model; // Declaring the model
		ResultSet result; // Declaring the data type variable where the query data will be stored
		
		// Defining the necessary paths for finding the files for the DB creation and fulfilling
	    String url="jdbc:sqlite:" + System.getProperty("user.dir") + "/coursesdb";
	    String urlDBcreation = System.getProperty("user.dir") + "/DBcreation.txt";
	    String urlDBinsertion = System.getProperty("user.dir") + "/DBinsertion.txt";
	    
	    Connection connect = stablishConnection(url); // Establishing the connection with the DB
	    File DBcretionFile = new File (urlDBcreation);
	    File DBinsertionFile = new File (urlDBinsertion);
	    
	    executeDBCreationFile(DBcretionFile, connect); // Creating the DB tables in DBcreation.txt file
	    executeDBInsertionFile(DBinsertionFile, connect); // Inserting the DB data of the DBinsertion.txt file

	}
	
	public static Connection stablishConnection(String url){
		try{
			// Trying to establish connection with the database called "coursesdb" in url variable
			Connection connect = DriverManager.getConnection(url);
                  
            if(connect != null) // Checking the connection
                System.out.println("Conection Succesful!");
            return connect;
            
        }catch(Exception x){
        	System.err.println(x.getMessage().toString());
        	return null;
        }
	}
	
	public static boolean executeSQLcommand(String command, Connection connect) { // Function to execute table creation commands
		try {
			PreparedStatement st = connect.prepareStatement(command);
			int result = st.executeUpdate();
			if(result > 0) // Check for errors
				return true;
			else
				return false;
		}catch(Exception x){
        	System.err.println(command + "\nCommand execution ERROR: " + x.getMessage().toString());
        	return false;
        }
	}
	
	public static ResultSet executeSQLquery(String command, Connection connect) { // Function to execute an SQL query
		try {
			ResultSet result; // To store the ResultSet value got from the DB
			PreparedStatement st = connect.prepareStatement(command);
			result = st.executeQuery(); // Execute the query in the DB
			if(result != null)
				return result;
			else
				return null;
		}catch(Exception x){
        	System.err.println("Query execution ERROR: " + x.getMessage().toString());
        	return null;
        }
	}
	
	public static void executeDBInsertionFile(File archive, Connection connect){
		
		FileReader fr = null;
		String sql;

	    try {
	       // Opening of the file and creation of the buffer to read the information
	       fr = new FileReader (archive);
	       sql = new String(Files.readAllBytes(Paths.get(archive.getAbsolutePath())));
	       
	       for (String commandunit : sql.split(";")) {
               if (commandunit.trim().length() > 0) {
                   executeSQLcommand(commandunit, connect); // Execute the command unit in the DB
                   System.out.println("Executed command: " + commandunit);
               }
           }
	    }
	    catch(Exception x){
	    	System.err.println("Insertion ERROR: " + x.getMessage().toString());
	    }finally{
	       // Finally, we close the file before ending the function execution
	       try{                    
	          if( null != fr ){   
	             fr.close();     
	          }                  
	       }catch (Exception e2){ 
	          e2.printStackTrace();
	       }
	    }
	}
	
	public static void executeDBCreationFile(File archive, Connection connect){
		FileReader fr = null;
	    String sql;

	    try {
	       // Opening of the file and creation of the buffer to read the information
	       sql = new String(Files.readAllBytes(Paths.get(archive.getAbsolutePath())));

	       for (String commandunit : sql.split(";")) {
               if (commandunit.trim().length() > 0) {
                   executeSQLcommand(commandunit, connect);
                   System.out.println("Executed command: " + commandunit);
               }
           }
	    }
	    catch(Exception x){
	    	System.err.println("Tables creation ERROR: " + x.getMessage().toString());
	    }finally{
	       // Finally, we close the file before ending the function execution
	       try{                    
	          if( null != fr ){   
	             fr.close();     
	          }                  
	       }catch (Exception e2){ 
	          e2.printStackTrace();
	       }
	    }
	}
}
