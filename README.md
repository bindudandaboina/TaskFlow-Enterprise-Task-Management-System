## Application Workflow

The TaskFlow application follows a structured workflow that allows users to register, log in, and manage tasks efficiently. Users can create tasks, view task details, update task information, mark tasks as completed, and delete tasks when they are no longer required.

### Workflow Diagram

```text
              Start
                |
                v
        User Registration
                |
                v
             User Login
                |
                v
       Session Management
                |
                v
         Task Dashboard
                |
                v
          Create Task
                |
                v
       Save Task in MySQL
                |
                v
           View Tasks
                |
                v
        Select a Task
                |
                v
       +--------+--------+
       |        |        |
       v        v        v
     Update   Complete  Delete
      Task     Task      Task
       |        |        |
       v        v        v
  Update DB  Update DB  Remove from DB
       |        |        |
       +--------+--------+
                |
                v
        Refresh Task List
                |
                v
      Manage More Tasks?
           /       \
         Yes        No
          |          |
          v          v
    Task Dashboard  Logout
                     |
                     v
                    End
```

### Workflow Description

1. **User Registration:** New users register by providing their details.
2. **User Login:** Registered users log in with their credentials.
3. **Session Management:** The application manages the user's session after successful login.
4. **Task Dashboard:** Users access the task management dashboard.
5. **Create Task:** Users enter the task name, description, story points, and assigned user.
6. **Save Task:** The application stores task information in the MySQL database using JDBC.
7. **View Tasks:** Users view the available task details.
8. **Update Task:** Users modify existing task information when necessary.
9. **Complete Task:** Users mark a task as completed, and the application updates its status in the database.
10. **Delete Task:** Users remove tasks that are no longer needed.
11. **Refresh Task List:** The application displays the latest task information after an operation.
12. **Logout:** Users log out to end their session.

### Technologies Involved

- **Frontend:** HTML, CSS, Bootstrap, JSP
- **Backend:** Java Servlets
- **Database Connectivity:** JDBC
- **Database:** MySQL
- **Server:** Apache Tomcat
- **Architecture:** Layered / MVC-style architecture

