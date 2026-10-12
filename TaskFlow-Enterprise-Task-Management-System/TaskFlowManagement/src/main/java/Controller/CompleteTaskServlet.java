package Controller;

import java.io.IOException;

import dao.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/completeTask")
public class CompleteTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        TaskDAO dao = new TaskDAO();
        dao.markAsCompleted(id);

        response.sendRedirect("task-home");
    }
}
