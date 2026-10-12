package Controller;

import java.io.IOException;

import dao.UserDAO;
import com.model.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Servlet 5
//genericservlet ab 2 
//Httpservlet
@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("request came for register user!!!");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmPassword=req.getParameter("confirmPassword");
		String role = req.getParameter("role");
		if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Passwords do not match!");
            RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
            rd.forward(req, resp);
            return;
        }
		Users user=new Users( name, email, password, role);
		
		System.out.println(user);
		
		UserDAO userdao=new UserDAO();
		boolean status = userdao.registerUser(user);
		if(status) {
			req.setAttribute("username", name);
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
		}
		else {
			req.setAttribute("error","E-mail already registered");
			RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
			rd.forward(req, resp);
		}
	}

}