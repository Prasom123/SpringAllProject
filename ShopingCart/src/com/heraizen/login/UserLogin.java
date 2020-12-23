package com.heraizen.login;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
          
        String username=request.getParameter("username");  
        String password=request.getParameter("password");  
          
        if(username.equals("badal")&&password.equals("badal123")){  
        request.getRequestDispatcher("home.jsp").include(request, response);  
        HttpSession session=request.getSession();  
        session.setAttribute("name",username);  
        }  
        else{  
           
            request.getRequestDispatcher("index.html").include(request, response);  
            out.print("Sorry, username or password error!");  
        }  
        out.close();  
    }  
	     
	
}
