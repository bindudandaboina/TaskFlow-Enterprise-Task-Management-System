package Controller;

import java.io.IOException;
import java.util.List;

import dao.TaskDAO;
import com.model.Tasks;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/task-home")
public class TaskListServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    System.out.println("request came for tasks list!!!!!");
	    System.out.println("servlet called");

	    TaskDAO taskdao = new TaskDAO();

	    // ✅ Get all tasks
	    List<Tasks> allTasks = taskdao.getAllTasks();
	    System.out.println("inTaskListServlet====== ");
	    System.out.println(allTasks);

	    // ✅ ADD THIS (VERY IMPORTANT)
	    int total = taskdao.getTotalTasks();
	    int completed = taskdao.getCompletedTasks();
	    int pending = taskdao.getPendingTasks();

	    // ✅ Debug
	    System.out.println("Total: " + total);
	    System.out.println("Completed: " + completed);
	    System.out.println("Pending: " + pending);

	    // ✅ Send data to JSP
	    req.setAttribute("allTasks", allTasks);
	    req.setAttribute("totalTasks", total);
	    req.setAttribute("completedTasks", completed);
	    req.setAttribute("pendingTasks", pending);

	    RequestDispatcher rd = req.getRequestDispatcher("task-home.jsp");
	    rd.forward(req, resp);
	}
}