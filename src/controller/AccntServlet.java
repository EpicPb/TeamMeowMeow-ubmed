package controller;

import java.io.IOException;
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


@WebServlet("/accountv.html")
public class AccntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		boolean logged = false;
		String accntNumber = request.getParameter("account");
	
		
		System.out.println(accntNumber);
		
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackatonn", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from accnt");
			
			while(rs.next()){
				if(accntNumber.equals(rs.getString("account"))){
						logged = true;
						
						System.out.println(accntNumber);
						
				}else{
					logged = false;
				}
			}
		}catch(Exception e){
			System.out.println("error on verify");
			
		}
		
		if(logged == true){
			Cookie userCookie = new Cookie("userCookie", accntNumber);
			userCookie.setMaxAge(1 * 365 * 24 * 60 * 60);
			response.addCookie(userCookie);
			
			response.sendRedirect("verified.html");
			
		}else{
			response.sendRedirect("errorlogin.html");
		}
	}
}
