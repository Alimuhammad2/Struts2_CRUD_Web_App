package Entity;

import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import DAO.crudOperations;

public class user {

	private int id;
	private user u;
	private String name;
	private String email;
	private String password;

	public user getU() {
		return u;
	}

	public void setU(user u) {
		this.u = u;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", u=" + u + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	private static final Logger logger = LogManager.getLogger(user.class);

	// Yeh method student ko add karta hai using 'crudOperations' class
	
	public String addstudent() {
		String add = crudOperations.addStudent(getName(), getEmail(), getPassword());
		return add;
	}

	public String getAllStudents() {
		return "success";
	}

	public String updateStudent() {
		u = crudOperations.getUserByCredentials(id);
		if (u != null) {
			crudOperations.updateStudentById(u);
			return "success";
		}
		return "fail";
	}

	// Yeh method updated student ko save karta hai
	
	public String updatedStudent() {
		
		user u = new user();
		u.setId(this.getId());
		u.setName(this.getName());
		u.setEmail(this.getEmail());
		u.setPassword(this.getPassword());
		
		crudOperations.updateStudentById(u);

		return "success";
	}

	public String deletedstudent() {

		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		crudOperations.deletestudent(id);
		return "success";
	
	}

	public String loginuser() {

		user u1 = crudOperations.getUserDetailsById(this);
		if (u1 != null) {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			try {

				// set user1 object in session
				session.setAttribute("user1", u1);

				logger.info("User Data Save in Session: " + u1.getName());

				String clientIP = (String) request.getAttribute("clientIP");

				// getting system IP
				InetAddress ip = InetAddress.getLocalHost();
				/*
				 * System.out.println(ip); System.out.println(ip.getHostName());
				 * System.out.println(ip.getHostAddress());
				 */
				 
				//request.setAttribute("u1", u1); // Store in request scope
				logger.info("Logged In User Id: " + u1.getId() + " Logged In User Name: " + u1.getName()
				+ " Logged In User Email: " + u1.getEmail() + " Logged In User Password: " + u1.getPassword());

				logger.info(clientIP);
				logger.info("System IP: " + ip);

				return "success";

			} catch (Exception e) {
				logger.error("Exception Message: " + e);
				return "fail";
			}
		}
		return "success";
	}

}
