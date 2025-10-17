package com.palle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Name = request.getParameter("tlName");
		String Email = request.getParameter("tlEmail");
		String Password = request.getParameter("tlPsd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBJEEAUG", "root", "1234");
			PreparedStatement pst = con.prepareStatement("SELECT * FROM DETAILS WHERE Email =? AND PASSWORD = ?");
			pst.setString(1, Email);
			pst.setString(2,Password);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				System.out.println("Login successful for: " + Name);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
			else {
				System.out.println("Login failed! for:"+Name);
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
