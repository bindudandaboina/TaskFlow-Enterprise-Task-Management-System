package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Tasks;
import utility.DBConnection;

public class TaskDAO {

	boolean status = false;

	public boolean createTask(Tasks task) {
		String sql = "INSERT INTO tasks (task_name,task_description,story_points,assigned_to) VALUES(?,?,?,?)";
		Connection con = DBConnection.getDBConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, task.getTaskName());
			st.setString(2, task.getTaskDescription());
			st.setInt(3, task.getStoryPoints());
			st.setString(4, task.getAssignedTo());
			st.setString(5,"Pending");
			int n = st.executeUpdate();
			if (n > 0) {
				System.out.println("task created successfully");
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	public boolean UpdateTask(Tasks uptask) {
		boolean status=false;
		String sql = "update  tasks set task_name=?,task_description=?,story_points=?,assigned_to=? where id=?";
		Connection con = DBConnection.getDBConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uptask.getTaskName());
			st.setString(2, uptask.getTaskDescription());
			st.setInt(3, uptask.getStoryPoints());
			st.setString(4, uptask.getAssignedTo());
			st.setInt(5, uptask.getTaskId());
			int n = st.executeUpdate();
			if (n > 0) {
				System.out.println("task updated successfully");
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	public boolean deleteTaskbyId(int taskid) {
		String sql = "delete from tasks where id=?";
		Connection con = DBConnection.getDBConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, taskid);
			int n = st.executeUpdate();
			if (n > 0) {
				System.out.println("task deleted successfully");
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	public Tasks FindTaskById(int taskid) {
		String sql = "select * from tasks where id=?";
		Connection con = DBConnection.getDBConnection();
		Tasks task = null;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, taskid);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				task=new Tasks();
               task.setTaskId(rs.getInt("id"));
               task.setTaskName(rs.getString("task_name"));
               task.setTaskDescription(rs.getString("task_description"));
               task.setStoryPoints(rs.getInt("story_points"));
               task.setAssignedTo(rs.getString("assigned_to"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return task;
	}
	
	
	public List<Tasks> getAllTasks() {
		List<Tasks> list=new ArrayList<>();
		
		String sql = "select * from tasks";
		Connection con = DBConnection.getDBConnection();
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Tasks task = new Tasks();//default
               task.setTaskId(rs.getInt("id"));
               task.setTaskName(rs.getString("task_name"));
               task.setTaskDescription(rs.getString("task_description"));
               task.setStoryPoints(rs.getInt("story_points"));
               task.setAssignedTo(rs.getString("assigned_to"));
               task.setStatus(rs.getString("status"));
               list.add(task);
			}
			System.out.println("in Task DAO==========");
			System.out.println(list);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return list;
	}
	public int getTotalTasks() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM tasks";
	    Connection con = DBConnection.getDBConnection();

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	public int getCompletedTasks() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM tasks WHERE LOWER(status)='completed'";
	    Connection con = DBConnection.getDBConnection();

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return count;
	}

	public int getPendingTasks() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM tasks WHERE LOWER(status)='pending'";
	    Connection con = DBConnection.getDBConnection();

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	public void markAsCompleted(int id) {
	    String sql = "UPDATE tasks SET status='Completed' WHERE id=?";
	    try {
	        Connection con = DBConnection.getDBConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}