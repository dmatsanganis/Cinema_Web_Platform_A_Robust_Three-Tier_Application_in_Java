

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AvailableMovies
 */
@WebServlet("/AvailableMovies")
public class AvailableMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();   //PrintWriter variable, out initialize.
		out.print("<!DOCTYPE html><html><head><link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\r\n" +
				"<script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>\r\n" +
				"<script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\r\n" +
				"<meta charset=\"UTF-8\"></head><body><h3>ALL AVAILABLE MOVIES</h3>" +
		"<table border='1' style=\"border: solid 1px #DDEEEE;border-collapse: collapse;border-spacing: 0;font: normal 13px Arial, sans-serif\">"
		+ "<tr><th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">MOVIE ID</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">TITLE</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">CATEGORY</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">DESCRIPTION</th></tr>");
		//Availiable Movies' Table appears.
		try
		{  //A try statement.
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager
	                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345"); //Connection with the postgresql database with following credentials.

			Statement stmt=con.createStatement(); //Creates Statement.
			ResultSet rs=stmt.executeQuery("select distinct movies.id,title,category,description\r\n" + 
					"from movies\r\n" + 
					"inner join provoles on movies.id=provoles.idmovie"); //Selects all the content from the database and the "movies" table.

			while(rs.next())  //A while statement.
			 {
				out.print("<tr><td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
				out.println(rs.getInt(1));
				out.print("</td>");
				out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
				out.println(rs.getString(2));
				out.print("</td>");
				out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
				out.println(rs.getString(3));
				out.print("</td>");
				out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
				out.println(rs.getString(4));
				out.print("</td></tr>");
				//Prints the Availiable Movies' Table.
			}
		}
		catch(Exception e)
		{ //Catch Statement.
			System.out.println(e); //Exception.
		}
		out.print("</table></body></html>"); //Close the html components.
	}

}
