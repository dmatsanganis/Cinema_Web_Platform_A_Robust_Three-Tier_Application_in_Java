import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AssingToCinema
 */
@WebServlet("/AssignToCinema")

public class AssignToCinema extends HttpServlet
 {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ //doPost void initialize with two objects and the required exceptions.
		try
		{  //A try statement.
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager
	                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345"); //Connection with the postgresql database with following credentials.
			int userid=0;
			PreparedStatement ps1=con.prepareStatement("select id from contentadmins where username=?;");
			ps1.setString(1,request.getParameter("username"));
			ResultSet rs = ps1.executeQuery();
			while(rs.next())  //A while statement.
			{
				userid = rs.getInt(1);
			}
			rs.close();
			ps1.close();  //Close the Prepared Statement variable, ps.
			PreparedStatement ps=con.prepareStatement(
			"insert into provoles(idmovie,start_date,end_date,contentadmin_id,cinemaid) values(?, ?, ?, ?, ?)");  //Prepared Statement to insert user's custom values into the database.
		     java.sql.Date s_date = java.sql.Date.valueOf(request.getParameter("start_date"));
		     java.sql.Date e_date = java.sql.Date.valueOf(request.getParameter("end_date"));
			 ps.setInt(1, Integer.valueOf(request.getParameter("movie_id")));
			 ps.setDate(2, s_date);
			 ps.setDate(3, e_date);
			 ps.setInt(4, userid);
			 ps.setInt(5, Integer.valueOf(request.getParameter("cinema_id")));
			 ps.executeUpdate();  //After getting the values execute an update.
			 ps.close();  //Close the Prepared Statement variable, ps.
	         con.close();  //Close the connection with the database.
	         PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
	         out.print("<div class=\"alert alert-success\">\r\n" +
						"  <strong>SUCCESS!</strong> Assigned Movie with ID : " + request.getParameter("movie_id") + " to Cinema with ID : "
						+ request.getParameter("cinema_id") + ".\r\n" + "</div>"); //Success Message appears into user's console.

				RequestDispatcher rd=request.getRequestDispatcher("AssignToCinema.jsp"); //RequestDispatcher variable, rd requests AssignToCinema.jsp .
				rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
				out.close(); //PrintWriter variable, out close.
	}
	catch(Exception e)
	{  //Catch statement.
		System.out.println(e);
		PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
		 out.print("<div class=\"alert alert-danger\">\r\n" +
					"  <strong>ERROR!</strong> Something went wrong.\r\n" +
					"</div>");
	  	RequestDispatcher rd=request.getRequestDispatcher("AssignToCinema.jsp"); //RequestDispatcher variable, rd requests AssignToCinema.jsp .
	  	rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
  	  out.close(); //PrintWriter variable, out close.
		}
	}
}
