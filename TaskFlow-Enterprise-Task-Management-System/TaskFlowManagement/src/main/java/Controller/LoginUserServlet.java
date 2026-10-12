package Controller;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userdao = new UserDAO();

        if (userdao.validateUser(email, password)) {

            HttpSession session = req.getSession();
            session.setAttribute("user", email);   // session created

            resp.sendRedirect("task-home");

        } else {
            req.setAttribute("error", "Invalid Email or Password");
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, resp);
        }
    }
}