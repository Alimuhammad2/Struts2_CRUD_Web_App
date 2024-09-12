<%@page import="DAO.crudOperations"%>
<%@page import="Entity.user"%>
<%@page import="java.util.List"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Data</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-color: #f8f9fa;
}

.table-container {
	margin: 50px auto;
	max-width: 900px;
}

.button-update, .button-delete {
	margin: 5px;
}
</style>
</head>
<body>

	<div class="table-container">
		<h2 class="text-center mb-4">User Data</h2>
		<s:form action="showdata" method="post">
			<table class="table table-bordered table-striped">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Name</th>
						<th scope="col">Email</th>
						<th scope="col">Password</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<user> myList = crudOperations.getAllStudents();
							for (user u : myList) {
					%>
					<tr>
						<td><%=u.getId()%></td>
						<td><%=u.getName()%></td>
						<td><%=u.getEmail()%></td>
						<td><%=u.getPassword()%></td>
						<td><a href="updatestudent.jsp?id=<%=u.getId()%>"
							class="btn btn-warning button-update">Update</a> <a href="deletestudent?id=<%=u.getId()%>"
								class="btn btn-danger button-delete"
								onclick="return confirm('Are you sure you want to delete this user?');">
								Delete
							</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<a href="userregister.jsp"class="btn btn-primary button-update">Add User</a>
		</s:form>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
