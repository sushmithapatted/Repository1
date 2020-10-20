package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

public class DBConnectorUtility {
	 private static DBConnection Db;
	 public static Connection connection;
	 
	 @Inject
	  public DBConnectorUtility(DBConnection Db) {
	    this.Db= Db;
	  }
	 
	  public static  boolean CreateData(String qry){
		  boolean flag=false;
		  try {
				connection= Db.getSqlConnectionDB();
				
				 if (connection != null) {
		                System.out.println("Connected to the database!");
		            } else {
		                System.out.println("Failed to make connection!");
		            }
				 Statement statement = connection.createStatement();
				 flag = statement.execute(qry);
		           
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		return flag;
		  
	  }
	  
	  public static  ResultSet ReadData(String qry){
		  ResultSet res=null;
		  try {
				connection= Db.getSqlConnectionDB();
				
				 if (connection != null) {
		                System.out.println("Connected to the database!");
		            } else {
		                System.out.println("Failed to make connection!");
		            }
				 Statement statement = connection.createStatement();
				 res = statement.getResultSet();
				
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		return res;
		  
	  }
}
