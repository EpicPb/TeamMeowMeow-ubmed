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

@WebServlet("/verify.html")
public class VerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		boolean logged = false;
		String name = request.getParameter("name");
		int balance = Integer.parseInt(request.getParameter("balance"));
		String bal="";
		
		System.out.println(name);
		System.out.println(balance);
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackatonn", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");
		
			while(rs.next()){
				if(balance <= rs.getInt("balance") || balance>1000000){
						logged = true;
						System.out.println("while" + name);
						System.out.println("while" + balance);
						
				}else{
					logged = false;
				}
			}
		}catch(Exception e){
			System.out.println("Error");
			
		}
		
		if(logged == true){
			Cookie userCookie = new Cookie("userCookie", bal);
			userCookie.setMaxAge(1 * 365 * 24 * 60 * 60);
			response.addCookie(userCookie);
			
			response.sendRedirect("success.html");
			
		}else{
			response.sendRedirect("errorlogin.html");
		}
	}

}
