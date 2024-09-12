package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import Connection.DBConnection;
import Entity.user;

public class crudOperations {

	static Connection con;
	private static final Logger logger = LogManager.getLogger(crudOperations.class);

	static {
		try {
			con = new DBConnection().getConnection();
		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
	}

	public static String addStudent(String name, String email, String password) {

		try {

			String sql = "insert into register(name, email, password) values (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);

			int i = ps.executeUpdate();

			if (i > 0) {
				logger.info("User Registered Successfully");
				return "success";
			} else {
				logger.error("user not Registered");
				return "fail";
			}

		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
		return "fail";
	}

	public static user getUserByCredentials(int id) {
		user user = null;
		try {

			String sql = "select * from register where id=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new user();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));

			}

		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
		return user;
	}

	public static List<user> getAllStudents() {

		List<user> list = new ArrayList<user>();
		user u;

		try {

			String sql = "select * from register";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				u = new user();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));
				logger.info("show all users");
				list.add(u);
				// return "success";
			}

		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
		return list;
	}

	public static String updateStudentById(user u) {

		try {

			String sql = "update register set name=?,email=?,password=? where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getId());

			int i = ps.executeUpdate();

			if (i == 1) {
				logger.info("user update successfully");
				return "success";
			} else {
				logger.error("user not updated..");
				return "fail";
			}
		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
		return "fail";
	}

	public static String deletestudent(int id) {

		try {

			String sql = "delete from register where id=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			int i = ps.executeUpdate();

			if (i == 1) {
				logger.info("user delete successfully");
				return "success";
			} else {
				logger.error("user not deleted");
				return "fail";
			}

		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
		return "fail";
	}

	public static user getUserDetailsById(user u) {

		user user = null;

		try {
			String sql = "select * from register where name=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());

			ResultSet rs = ps.executeQuery();
			user user1;
			if (rs.next()) {
				user1 = new user();
				user1.setId(rs.getInt(1));
				user1.setName(rs.getString(2));
				user1.setEmail(rs.getString(3));
				user1.setPassword(rs.getString(4));
				logger.info("Login Successful for User: " + user1.getName());
				return user1;

			} else {
				logger.error("Login Failed");
				return null;
			}
		} catch (Exception e) {
			logger.error("Exception Message: " + e);
			return null;
		}
	}
}
