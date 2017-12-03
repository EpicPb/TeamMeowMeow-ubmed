package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.html")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		boolean logged = false;
		String UserName = request.getParameter("userName");
		String Password = request.getParameter("password");
		
		System.out.println(UserName);
		System.out.println(Password);
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackatonn", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");
			
			while(rs.next()){
				if(UserName.equals(rs.getString("userName")) 
						&& Password.equals(rs.getString("password"))){
						logged = true;
						System.out.println("while" + UserName);
						System.out.println(Password);
						
				}else{
					logged = false;
				}
			}
		}catch(Exception e){
			System.out.println("error on login");
			
		}
		
		if(logged == true){
			Cookie userCookie = new Cookie("userCookie", UserName);
			userCookie.setMaxAge(1 * 365 * 24 * 60 * 60);
			response.addCookie(userCookie);
			
			response.sendRedirect("enterAccNo.html");
			
		}else{
			response.sendRedirect("errorlogin.html");
		}
	}

}
