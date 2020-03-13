

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{  //A try statement.
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager
	                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345"); //Connection with the postgresql database with following credentials.

			SecureRandom random = new SecureRandom();
			byte bytes[]= new byte[20];
			random.nextBytes(bytes);
			String salt = random.toString();
			
			String hashedpassword = Encryption.getHashMD5(request.getParameter("password"),salt);
			
			PreparedStatement ps=con.prepareStatement(
					"insert into clients(username,hashedpassword,salt,fullname) values(?, ?, ?, ?)");  //Prepared Statement to insert the movie's values into the database.
					 ps.setString(1,request.getParameter("username"));
					 ps.setString(2,hashedpassword);
					 ps.setString(3,salt);
					 ps.setString(4,request.getParameter("fullname"));
					 ps.executeUpdate();  //After getting the values execute an update.
					 ps.close();  //Close the Prepared Statement variable, ps.
			         con.close();  //Close the connection with the database.
			         PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
			         out.print("<div class=\"alert alert-success\">\r\n" +
								"  <strong>SUCCESS!</strong> Client added to the database.\r\n" +
								"</div>"); //Success Message appears into user's console.
			         RequestDispatcher rd=request.getRequestDispatcher("Register.jsp"); //RequestDispatcher variable, rd requests AddMovie.jsp .
			         rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
			         out.close();
		}//PrintWriter variable, out close.
		catch(Exception e)
 		{  //Catch statement.
 			System.out.println(e);
 			PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
 			 out.print("<div class=\"alert alert-danger\">\r\n" +
 						"  <strong>ERROR!</strong> Something went wrong.\r\n" +
 						"</div>"); //Excption's Error Message appears.
 				RequestDispatcher rd=request.getRequestDispatcher("Register.jsp"); //RequestDispatcher variable, rd requests AddMovie.jsp .
 				rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
 				out.close(); //PrintWriter variable, out close.
 		}	        

	}

}
