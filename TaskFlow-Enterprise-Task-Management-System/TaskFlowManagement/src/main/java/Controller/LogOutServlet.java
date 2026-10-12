package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
   
    public LogOutServlet() {
        super();
       
    }

	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate(); // destroy session
        }

        resp.sendRedirect("login.jsp");
    }

	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		\
//		doGet(request, response);
//	}

}
