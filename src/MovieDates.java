

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class MovieDates
 */
@WebServlet("/MovieDates")
public class MovieDates extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();   //PrintWriter variable, out initialize.
		out.print("<!DOCTYPE html><html><head><link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\r\n" +
				"<script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>\r\n" +
				"<script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\r\n" +
				"<meta charset=\"UTF-8\"></head><body><h3>ALL AVAILABLE MOVIES FOR YOUR DATES</h3>" +
		"<table border='1' style=\"border: solid 1px #DDEEEE;border-collapse: collapse;border-spacing: 0;font: normal 13px Arial, sans-serif\">"
		+ "<tr><th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">PROVOLI ID</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">MOVIE TITLE</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">START DATE</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">END DATE</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">CONTENT ADMIN</th>"
		+ "<th style=\"background-color: #DDEFEF;border: solid 1px #DDEEEE;color: #336B6B;padding: 10px;text-align: left;text-shadow: 1px 1px 1px #fff; \">CINEMA ID</th></tr>");
		//Availiable Movies' Table appears.
		try
		{  //A try statement.
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager
	                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345"); //Connection with the postgresql database with following credentials.

			PreparedStatement ps=con.prepareStatement(
			"select provoles.id,movies.title,start_date,end_date,contentadmins.fullname,cinemaid\r\n" + 
					"from provoles,movies,contentadmins\r\n" + 
					"where contentadmins.id=provoles.contentadmin_id and provoles.idmovie=movies.id and start_date between ? and ?;");
					//"where start_date between ? and ?;"); //Selects all the content from the database and the "movies" table.
			java.sql.Date s_date = java.sql.Date.valueOf(request.getParameter("start_date"));
			java.sql.Date e_date = java.sql.Date.valueOf(request.getParameter("end_date"));
		    ps.setDate(1, s_date); 
		    ps.setDate(2, e_date);
			ResultSet rs=ps.executeQuery();
			 while (rs.next()) {
		               DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		               String strDate1 = dateFormat.format(rs.getDate(3));  
		               String strDate2 = dateFormat.format(rs.getDate(4));
		               
		                out.print("<tr><td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
						out.println(String.valueOf(rs.getInt(1)));
						out.print("</td>");
						out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
						out.println(rs.getString(2));
						out.print("</td>");
						out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
						out.println(strDate1);
						out.print("</td>");
						out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
						out.println(strDate2);
						out.print("</td>");
						out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
						out.println(String.valueOf(rs.getString(5)));
						out.print("</td>");
						out.print("<td style=\"border: solid 1px #DDEEEE;color: #333;padding: 10px; text-shadow: 1px 1px 1px #fff;\">");
						out.println(String.valueOf(rs.getInt(6)));
						out.print("</td></tr>");
			   }
		   rs.close();
		   ps.close();
		   con.close();
		}
		catch(Exception e)
		{ //Catch Statement.
			System.out.println(e); //Exception.
		}
		out.print("</table></body></html>"); //Close the html components.
	}

}
