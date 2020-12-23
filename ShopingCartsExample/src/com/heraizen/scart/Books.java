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

import com.heraizen.domain.BookList;
import com.heraizen.util.MongoDBConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Books
 */
@WebServlet("/Books")
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
        
		

		HttpSession session = request.getSession(true);
		if (session != null) {
			List<BookList> BookListDetails = new ArrayList<>();

			try (MongoClient client = MongoDBConnection.getMongoClient()) {
				MongoDatabase database = client.getDatabase("teamsinfo");
				MongoCollection<BookList> collection = database.getCollection("bookdata", BookList.class);
				FindIterable<BookList> bookDetailsObj = collection.find();
				bookDetailsObj.forEach(book -> {
					BookListDetails.add(book);
				});

			}
			request.setAttribute("books",BookListDetails);
			request.getRequestDispatcher("showbook.jsp").include(request, response);

			StringBuilder sb = new StringBuilder();
			sb.append("<div class='col-md-12 mt-5' >");
			sb.append("<table class='table table-bordered'>" + "<tr>" + "<th>Authorname</th>" + "<th>Country</th>"
					+ "<th>Language</th>" + "<th>Title</th>" + "<th>Page</th>" + "<th>Year</th>" + "</tr>");
			BookListDetails.stream().forEach(book -> {

				sb.append("<tr>" + "<td>" + book.getAuthor() + "</td>" + "<td>" + book.getCountry() + "</td>" + "<td>"
						+ book.getLanguage() + "</td>" + "<td>" + book.getTitle() + "</td>" + "<td>" + book.getPages()
						+ "</td>" + "<td>" + book.getYear() + "</td>" + "</tr>");
			});
			sb.append("</table>" + "</div>");
			out.print(sb);
		} else {
			out.print("Please login first");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
