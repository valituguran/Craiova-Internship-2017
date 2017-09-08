<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 8/28/2017
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">

</head>
<body>
<a href="register.jsp">Register an user</a>
<form action="logoutServlet" method="post">
    <input type="submit" value="Logout" />
    <a href="BNRCurrency.jsp">Update the Currency Values</a>
</form>
<form class="form" method="get" action="ParseHistoryServlet" >
    <input type="submit" value="Insert history in database"><br>
</form>
</body>
</html>
