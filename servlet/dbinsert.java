package com.demo.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbinsert")
public class dbinsert extends HttpServlet {
	Connection connection = null;
	@Override
	public void init() throws ServletException {
		try {
			System.out.println("INIT INVOKED");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}		
	}
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int busNumber = Integer.parseInt(req.getParameter("busNumber"));
	String driverName = req.getParameter("driverName");
	insertDetails(busNumber, driverName);
}

public void insertDetails(int busNumber, String driverName) {
	// Get ojdbc14.jar
	// Load the driver
	try {
		//Create the statement
		Statement statement = connection.createStatement();
		//Execute the query
		int noOfRowsInserted = statement.executeUpdate("insert into bus values(" + busNumber + ", '" + driverName + "')" );
		if(noOfRowsInserted ==1) {
			System.out.println("NO OF ROWS INSERTED : " + noOfRowsInserted);
		}
		else {
			System.out.println("No rows inserted!");
		}
	} catch (SQLException e) {
		System.out.println(e);
	}
}
}