import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")

public class Logout extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  //doPost void initialize with two objects and the required exceptions.

		HttpSession session = request.getSession(); //Creates the session.
		session.removeAttribute("username");  //Removes the username's attribute previous content.
		session.invalidate();  // Removes the session from the registry.
		response.sendRedirect("login.html"); //Requests the login.html form after user's logout.

	}
}
