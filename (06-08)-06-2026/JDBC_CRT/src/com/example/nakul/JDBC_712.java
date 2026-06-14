package com.example.nakul;

import java.sql.*;
public class JDBC_712 {
	public static void main(String[] args) {
		
		String url="jdbc:mysql://localhost:9096/ycce_b3";
		String user="root";
		String pass="root";
		
		try {
			// Load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// connect to database
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("Database connected successfully");
			
			// create statement
			Statement stmt= con.createStatement();
			String createTable="CREATE TABLE IF NOT EXISTS "
					+ " employees "
					+ " ("+" emp_no INT PRIMARY KEY," + " "
					+ "emp_name VARCHAR(100), "+" emp_salary DOUBLE) ";
			
			//execute query
			stmt.executeUpdate(createTable);
			System.out.println("Table created successfully : 'employees'");
			
			// insert the data
			String insertQuery="INSERT INTO "
					+ "employees (emp_no,emp_name,emp_salary)"
					+ "VALUES(?,?,?)";
			
			PreparedStatement pst=con.prepareStatement(insertQuery);
			
			//insert first employee data
			pst.setInt(1,111);
			pst.setString(2, "Nakul");
			pst.setDouble(3, 12345.56);
			pst.executeUpdate();
			
			//insert second employee data
			pst.setInt(1,112);
			pst.setString(2, "Shantanu");
			pst.setDouble(3, 12354.12);
			pst.executeUpdate();
			
			//insert third employee data
			pst.setInt(1,113);
			pst.setString(2, "Pawan");
			pst.setDouble(3, 92851.72);
			pst.executeUpdate();
			
			//insert fourth employee data
			pst.setInt(1,114);
			pst.setString(2, "Laleet");
			pst.setDouble(3, 38298.91);
			pst.executeUpdate();
			
			//insert fifth employee data
			pst.setInt(1,115);
			pst.setString(2, "Shivam");
			pst.setDouble(3, 21931.56);
			pst.executeUpdate();
			
			System.out.println("Employees data inserted successfully");
			
			
			//close connection
			con.close();
			}catch(Exception e) {
				e.printStackTrace();
			
		}

	}

}