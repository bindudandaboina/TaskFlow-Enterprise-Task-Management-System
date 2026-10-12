<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Tasks" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Management</title>

<style>
body {
    font-family: Arial, sans-serif;
    background: #f5f3ff;
    margin: 0;
}

.header {
    height: 70px;
    background: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 30px;
    box-shadow: 0 3px 10px rgba(0,0,0,0.1);
}

.logout-btn {
    text-decoration: none;
    padding: 8px 15px;
    border: 2px solid #764ba2;
    border-radius: 8px;
    color: #764ba2;
}

.main {
    width: 90%;
    margin: auto;
    padding: 20px;
}

.create-btn {
    padding: 10px 15px;
    background: #667eea;
    color: white;
    text-decoration: none;
    border-radius: 8px;
}

table {
    width: 100%;
    margin-top: 20px;
    border-collapse: collapse;
    background: white;
}

th {
    background: #667eea;
    color: white;
    padding: 10px;
}

td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
}
</style>

</head>

<body>

<header class="header">
    <h2>Task Management</h2>
    <a href="logout" class="logout-btn">Logout</a>
</header>

<main class="main">

    <h3>My Tasks</h3>
    <a href="task-form.jsp" class="create-btn">+ Create Task</a>
<style>
.dashboard {
    display: flex;
    gap: 20px;
    margin-top: 20px;
}

.card {
    flex: 1;
    padding: 20px;
    border-radius: 10px;
    color: white;
    text-align: center;
    font-weight: bold;
}

.total { background: #667eea; }
.completed { background: #28a745; }
.pending { background: #dc3545; }
</style>

<%
Integer total = (Integer) request.getAttribute("totalTasks");
Integer completed = (Integer) request.getAttribute("completedTasks");
Integer pending = (Integer) request.getAttribute("pendingTasks");
%>

<div class="dashboard">

    <div class="card total">
        <h3>Total Tasks</h3>
        <h2><%= (total != null) ? total : 0 %></h2>
    </div>

    <div class="card completed">
        <h3>Completed</h3>
        <h2><%= (completed != null) ? completed : 0 %></h2>
    </div>

    <div class="card pending">
        <h3>Pending</h3>
        <h2><%= (pending != null) ? pending : 0 %></h2>
    </div>

</div>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Story Points</th>
            <th>Assigned To</th>
            <th>Action</th>
        </tr>

        <%
            List<Tasks> alltasks = (List<Tasks>) request.getAttribute("allTasks");

            if (alltasks != null && !alltasks.isEmpty()) {
                for (Tasks task : alltasks) {
        %>

        <tr>
            <td><%= task.getTaskId() %></td>
            <td><%= task.getTaskName() %></td>
            <td><%= task.getTaskDescription() %></td>
            <td><%= task.getStoryPoints() %></td>
            <td><%= task.getAssignedTo() %></td>
            <td>
    <a href="updateTask?id=<%= task.getTaskId() %>">Update</a> |

    <a href="deleteTask?id=<%= task.getTaskId() %>"
       onclick="return confirm('Are you sure you want to delete this task?');">
       Delete
    </a> |

    <% if (!"Completed".equalsIgnoreCase(task.getStatus())) { %>

        <a href="completeTask?id=<%= task.getTaskId() %>">
            Complete
        </a>

    <% } else { %>

        ✅ Completed

    <% } %>

</td>
        </tr>

        <%
                }
            } else {
        %>

        <tr>
            <td colspan="6" style="text-align:center;">No Tasks Available</td>
        </tr>

        <%
            }
        %>

    </table>

</main>

</body>
</html>