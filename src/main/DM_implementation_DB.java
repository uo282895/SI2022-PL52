package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.io.*;

public class DM_implementation_DB {
	    
	public static void main(String[] args) {
		
		DefaultTableModel model; // Declaring the model
		
		// Defining the necessary paths for finding the files for the DB creation and fulfilling
	    String url="jdbc:sqlite:" + System.getProperty("user.dir") + "/coursesdb";
	    String urlDBcreation = System.getProperty("user.dir") + "/DBcreation.txt";
	    
	    Connection connect = stablishConnection(url); // Establishing the connection with the DB
	    File archive = new File (urlDBcreation);
		
	    ResultSet result = null; // Variable that will store the result of a SQL query
	    		
	}
	
	public static Connection stablishConnection(String url) {
		try{
			// Trying to establish connection with the database called "coursesdb" in url variable
			Connection connect = DriverManager.getConnection(url);
                  
            if(connect != null) // Checking the connection
                System.out.println("Conection Succesful!");
            return connect;
            /*
            executeSQLcommand("drop table athlete;", connect);
            executeSQLcommand("CREATE TABLE athlete (", connect);
            executeSQLcommand("athlete_id INTEGER PRIMARY KEY,", connect);
            executeSQLcommand("name TEXT NOT NULL,", connect);
            executeSQLcommand("date_of_birth TEXT NOT NULL,", connect);
            executeSQLcommand("sex TEXT NOT NULL);", connect);
            */
        }catch(Exception x){
        	System.err.println(x.getMessage().toString());
        	return null;
        }
	}
	
	public static boolean executeSQLcommand(String command, Connection connect) { // Function to execute table creation commands
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
	
	public void executeDBCreationFile(File archive, Connection connect) {
		FileReader fr = null;
	    BufferedReader br = null;

	    try {
	       // Opening of the file and creation of the buffer to read the information
	       fr = new FileReader (archive);
	       br = new BufferedReader(fr);

	       // Execute each line of the file as a SQL command for the tables creation
	       String line;
	       while((line=br.readLine())!=null)
	          executeSQLcommand(line, connect);
	    }
	    catch(Exception e){
	       e.printStackTrace();
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
