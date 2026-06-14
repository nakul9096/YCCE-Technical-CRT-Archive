package com.example.nakul;
import java.sql.*;

public class JDBC_711 {
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:9096/";
		String user="root";
		String password="root";
		
		String dbName="ycce_b3";
		try {
			//step 2--> Load & Register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Step 3 -->connect database 
			Connection con =DriverManager.getConnection(url,user,password);
			System.out.print("Database Connected Sucessfully");
			
			//step4-->Create Statement.
			Statement stmt =con.createStatement();
			String query=("CREATE DATABASE "+ dbName);
			
			//step5--> execute query
			stmt.executeUpdate(query);
			
			//step 6--> Result 
			System.out.println("Databsae created stucessfu;ly:: "+dbName);
			
			//step 7-->close connection
			con.close();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}