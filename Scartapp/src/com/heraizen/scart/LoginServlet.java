package com.heraizen.scart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heraizen.domain.User;
import com.heraizen.util.MongoDBConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		List<User> userDetails = new ArrayList<User>();
		
		try (MongoClient client = MongoDBConnection.getMongoClient()) {
			MongoDatabase database = client.getDatabase("teamsinfo");
			MongoCollection<User> collection = database.getCollection("userdetails", User.class);
			FindIterable<User> userDetailsobj = collection.find();
			userDetailsobj.forEach(user -> {
				userDetails.add(user);
			});

		}
          
		userDetails.stream().forEach(user -> {
			if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				try {
					request.getRequestDispatcher("home.jsp").include(request, response);
					
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HttpSession session = request.getSession();
				session.setAttribute("name", username);
			} else {

				try {
					request.getRequestDispatcher("index.html").include(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("<div class='col-md-12 text-danger text-center'>"+"Username and password are invalid !"+"<div>");
			}

			out.close();
		});

	}

}
