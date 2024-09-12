<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
    <script type="text/javascript">
        function redirectToLogin() {
            window.location.href = 'userlogin.jsp'; 
        }
        <s:if test="#session.user1 == null">
 
            redirectToLogin();
            
        </s:if>
    </script>
</head>
<body>
<!-- Check Sessions here -->
<s:if test="#session.user1 != null">
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><s:property value="#session.user1.id" /></td>
                <td><s:property value="#session.user1.name" /></td>
                <td><s:property value="#session.user1.email" /></td>
                <td><s:property value="#session.user1.password" /></td>
            </tr>
        </tbody>
    </table>
    <s:form action="logout" method="post">
        <s:submit value="Logout"/>
    </s:form>
</s:if>
<s:else>
    <p>No user data found. Please log in.</p>
</s:else>
<%-- <script>
function confirmLogout() {
    let text = "Are you sure you want to logout?";
    return confirm(text); // returns true if OK is pressed, false otherwise
}
</script> --%>

</body>
</html>