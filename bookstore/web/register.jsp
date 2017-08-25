<%--
  Created by IntelliJ IDEA.
  User: madalina.luca
  Date: 7/27/2017
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../styles/layout_login.css">
    <title>Add users</title>
</head>
<body>
<form method="post" action="registerServlet">
    <header>Register</header>
    <label for="username"> Username:</label>
    <input type="text" id="username" name="username" required="required">

    <label for="userpass"> Parola:</label>
    <input type="password" id="userpass" name="userpass" required="required">

    <label for="email"> Email:</label>
    <input type="text" id="email" name="email" required="required">

    <label for="realname"> Nume:</label>
    <input type="text" id="realname" name="realname" required="required">
    <button type="submit">Adauga user</button>
</form>
</body>
</html>
