package com.palle;

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
import java.sql.Statement;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String Name = request.getParameter("tbName");
			String Email = request.getParameter("tbEmail");
			String Password = request.getParameter("tbPsd");
			
			//out.println(Email);
			//out.println(Password);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBJEEAUG", "root", "1234");
				Statement st=con.createStatement();
				int RowsEffected = st.executeUpdate("CREATE TABLE IF NOT EXISTS DETAILS(NAME VARCHAR(20),EMAIL varchar(20),PASSWORD VARCHAR(20))");
				out.println("Table created"+RowsEffected);
				st.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBJEEAUG", "root", "1234");
				PreparedStatement pst=con.prepareStatement("SELECT * FROM DETAILS WHERE EMAIL = ? AND PASSWORD =?");
				pst.setString(1, Email);
				pst.setString(2, Password);
				ResultSet rst = pst.executeQuery();
				if(rst.next()) {
					//response.sendRedirect("login.html");
					out.println("<h3>User already registered. Please <a href='login.html'>login here</a>.</h3>");
				}
				else {
					PreparedStatement pst1 = con.prepareStatement("INSERT INTO DETAILS(NAME,EMAIL,PASSWORD) VALUES(?,?,?)");
					pst1.setString(1, Name);
					pst1.setString(2, Email);
					pst1.setString(3, Password);
					int rowsInserted = pst1.executeUpdate();
					if(rowsInserted > 0) {
						out.println("<h3>Registration successful!</h3>");
						response.sendRedirect("login.html");
					}
					else {
						  out.println("<h3>Registration failed. Please try again.</h3>");
					}
				}
				pst.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
