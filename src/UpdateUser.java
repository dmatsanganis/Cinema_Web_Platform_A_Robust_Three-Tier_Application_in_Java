

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
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
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String r = request.getParameter("role");
		
		SecureRandom random = new SecureRandom();
		byte bytes[]= new byte[20];
		random.nextBytes(bytes);
		String salt = random.toString();
		String hashedpassword = Encryption.getHashMD5(request.getParameter("old_password"),salt);

		if(r.equals("contentadmins")) {
			try
			{  //A try statement.
				Class.forName("org.postgresql.Driver");
				Connection con=DriverManager
		                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345");
				
				int creator=0;
				PreparedStatement ps2=con.prepareStatement("select id from admins where username=?;");
				ps2.setString(1,request.getParameter("creator"));
				ResultSet rs1 = ps2.executeQuery();
				while(rs1.next())  //A while statement.
				{
					creator = rs1.getInt(1);
				}
				rs1.close();
				ps2.close();
				 //Connection with the postgresql database with following credentials.
				PreparedStatement ps=con.prepareStatement(
						"update contentadmins set username=? , hashedpassword=?, salt=?, fullname=?, createdby_admin=? where username=? ");  //Prepared Statement to insert the movie's values into the database.
						 ps.setString(1,request.getParameter("new_username"));
						 ps.setString(2,hashedpassword);
						 ps.setString(3,salt);
						 ps.setString(4,request.getParameter("fullname"));
						 ps.setInt(5,creator);
						 ps.setString(6,request.getParameter("old_username"));
						 ps.executeUpdate();  //After getting the values execute an update.
						 ps.close();  //Close the Prepared Statement variable, ps.
				         con.close();  //Close the connection with the database.
				         PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
				         out.print("<div class=\"alert alert-success\">\r\n" +
									"  <strong>SUCCESS!</strong> Content Admin updated to the database.\r\n" +
									"</div>"); //Success Message appears into user's console.
				         RequestDispatcher rd=request.getRequestDispatcher("UpdateUser.jsp"); //RequestDispatcher variable, rd requests AddMovie.jsp .
				         rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
				         out.close(); //PrintWriter variable, out close.

			}//Close the Prepared Statement variable, ps.
				catch(Exception e)
				{  //Catch statement.
					System.out.println(e);
					PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
					 out.print("<div class=\"alert alert-danger\">\r\n" +
								"  <strong>ERROR!</strong> Something went wrong.\r\n" +
								"</div>"); //Excption's Error Message appears.
						RequestDispatcher rd=request.getRequestDispatcher("UpdateUser.jsp"); //RequestDispatcher variable, rd requests DeleteMovie.jsp .
						rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
						out.close(); //PrintWriter variable, out close.
				}
		}
		else if(r.equals("admins")) {
			try
			{  //A try statement.
				Class.forName("org.postgresql.Driver");
				Connection con=DriverManager
		                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345");
				
				int creator=0;
				PreparedStatement ps2=con.prepareStatement("select id from admins where username=?;");
				ps2.setString(1,request.getParameter("creator"));
				ResultSet rs1 = ps2.executeQuery();
				while(rs1.next())  //A while statement.
				{
					creator = rs1.getInt(1);
				}
				rs1.close();
				ps2.close();
				
				PreparedStatement ps=con.prepareStatement(
						"update admins set username=? , hashedpassword=?,salt=?, fullname=?, createdby_admin=? where username=? ");  //Prepared Statement to insert the movie's values into the database.
						 ps.setString(1,request.getParameter("new_username"));
						 ps.setString(2,hashedpassword);
						 ps.setString(3,salt);
						 ps.setString(4,request.getParameter("fullname"));
						 ps.setInt(5,creator);
						 ps.setString(6,request.getParameter("old_username"));
						 ps.executeUpdate();  //After getting the values execute an update.
						 ps.close();  //Close the Prepared Statement variable, ps.
				         con.close();  //Close the connection with the database.
				         PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
				         out.print("<div class=\"alert alert-success\">\r\n" +
									"  <strong>SUCCESS!</strong> Admin updated to the database.\r\n" +
									"</div>"); //Success Message appears into user's console.
				         RequestDispatcher rd=request.getRequestDispatcher("UpdateUser.jsp"); //RequestDispatcher variable, rd requests AddMovie.jsp .
				         rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
				         out.close(); //PrintWriter variable, out close.

			}//Close the Prepared Statement variable, ps.
				catch(Exception e)
				{  //Catch statement.
					System.out.println(e);
					PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
					 out.print("<div class=\"alert alert-danger\">\r\n" +
								"  <strong>ERROR!</strong> Something went wrong.\r\n" +
								"</div>"); //Excption's Error Message appears.
						RequestDispatcher rd=request.getRequestDispatcher("UpdateUser.jsp"); //RequestDispatcher variable, rd requests DeleteMovie.jsp .
						rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
						out.close(); //PrintWriter variable, out close.
				}
		}
		else {
			try
			{  
				Class.forName("org.postgresql.Driver");
				Connection con=DriverManager
		                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345");
			PreparedStatement ps=con.prepareStatement(
					"update clients set username=? , hashedpassword=?,salt=?, fullname=? where username=? ");  //Prepared Statement to insert the movie's values into the database.
					 ps.setString(1,request.getParameter("new_username"));
					 ps.setString(2,hashedpassword);
					 ps.setString(3,salt);
					 ps.setString(4,request.getParameter("fullname"));
					 ps.setString(5,request.getParameter("old_username"));
					 ps.executeUpdate();  //After getting the values execute an update.
					 ps.close();  //Close the Prepared Statement variable, ps.
			         con.close();  //Close the connection with the database.
			         PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
			         out.print("<div class=\"alert alert-success\">\r\n" +
								"  <strong>SUCCESS!</strong> Client updated to the database.\r\n" +
								"</div>"); //Success Message appears into user's console.
			         RequestDispatcher rd=request.getRequestDispatcher("UpdateUser.jsp"); //RequestDispatcher variable, rd requests AddMovie.jsp .
			         rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
			         out.close(); //PrintWriter variable, out close.

		}//Close the Prepared Statement variable, ps.
			catch(Exception e)
			{  //Catch statement.
				System.out.println(e);
				PrintWriter out = response.getWriter(); //PrintWriter variable, out initialize.
				 out.print("<div class=\"alert alert-danger\">\r\n" +
							"  <strong>ERROR!</strong> Something went wrong.\r\n" +
							"</div>"); //Excption's Error Message appears.
					RequestDispatcher rd=request.getRequestDispatcher("UpdateUser.jsp"); //RequestDispatcher variable, rd requests DeleteMovie.jsp .
					rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
					out.close(); //PrintWriter variable, out close.
			}
	
		}
	}
}
