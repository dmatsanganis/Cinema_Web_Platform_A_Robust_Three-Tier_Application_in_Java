

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteMovie
 */
@WebServlet("/DeleteMovie")
public class DeleteMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{//doPost void initialize with two objects and the required exceptions.
		try
		{  //A try statement.
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager
	                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345"); //Connection with the postgresql database with following credentials.

			PreparedStatement ps=con.prepareStatement(
			"delete from reservations where provoli_id in (select id from provoles where idmovie=?);"
			+ "delete from provoles where idmovie=?;"	
			+ "delete from movies where id=?;");  //Prepared Statement to delete the movie from the database.
			ps.setInt(1, Integer.valueOf(request.getParameter("id")));
			ps.setInt(2, Integer.valueOf(request.getParameter("id")));
			ps.setInt(3, Integer.valueOf(request.getParameter("id")));
			ps.executeUpdate();  //After getting the values execute an update.
			ps.close();  //Close the Prepared Statement variable, ps.
	        con.close();  //Close the connection with the database.
	        PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
	        out.print("<div class=\"alert alert-success\">\r\n" +
					  "  <strong>SUCCESS!</strong> Movie deleted the database.\r\n" +
					  "</div>"); //Success Message appears into user's console.
	        RequestDispatcher rd=request.getRequestDispatcher("DeleteMovie.jsp"); //RequestDispatcher variable, rd requests DeleteMovie.jsp .
			rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
			out.close(); //PrintWriter variable, out close.
		}
		catch(Exception e)
		{  //Catch statement.
			System.out.println(e);
			PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
			 out.print("<div class=\"alert alert-danger\">\r\n" +
						"  <strong>ERROR!</strong> Something went wrong.\r\n" +
						"</div>"); //Excption's Error Message appears.
				RequestDispatcher rd=request.getRequestDispatcher("DeleteMovie.jsp"); //RequestDispatcher variable, rd requests DeleteMovie.jsp .
				rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
				out.close(); //PrintWriter variable, out close.
		}
	}

}
