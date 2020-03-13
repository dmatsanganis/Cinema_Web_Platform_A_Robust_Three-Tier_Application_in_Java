import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 { //doPost void initialize with two objects and the required exceptions.

		response.setContentType("text/html"); //response's content is now specified.
		PrintWriter out = response.getWriter();  //PrintWriter variable, out initialize.
		String n = request.getParameter("username");  //String type valiable, n gets the username input.
		String p = request.getParameter("password");  //String type valiable, p gets the password input.
		String r = request.getParameter("role");  //String type valiable, r gets the role input.
		if(r.equals("contentadmins") && LoginDao.validate(n,p,r)) //If the Login.Dao validates the user's credentials then:
		{
			HttpSession session = request.getSession(); //A new session is created.
			session.setAttribute("username", n);  // With the aforementioned username.
			response.sendRedirect("ContentAdmin.jsp");  //Gets the ContentAdmin.jsp page.
		}
		else if(r.equals("admins") && LoginDao.validate(n,p,r)) {
			HttpSession session = request.getSession(); //A new session is created.
			session.setAttribute("username", n);  // With the aforementioned username.
			response.sendRedirect("Admin.jsp");  //Gets the Admin.jsp page.
		}
		else if(r.equals("clients") && LoginDao.validate(n,p,r)) {
			HttpSession session = request.getSession(); //A new session is created.
			session.setAttribute("username", n);  // With the aforementioned username.
			response.sendRedirect("Client.jsp");  //Gets the Client.jsp page.
		}
		else //Else, if Login.Dao does not validate the user's credentials then:
		{
			out.print("<div class=\"alert alert-danger\">\r\n" +
					"  <strong>SORRY INVALID USERNAME OR PASSWORD!</strong> Please try again.\r\n" +  
					"</div>"); //Login Failure Message appears.

			RequestDispatcher rd=request.getRequestDispatcher("login.html");//RequestDispatcher variable, rd requests login.html in order to retry to login.
			rd.include(request,response); //RequestDispatcher variable, rd includes two objects (request,response).
			out.close(); //PrintWriter variable, out close.
		}
	}
}
