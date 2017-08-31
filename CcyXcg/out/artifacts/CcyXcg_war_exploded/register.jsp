<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 8/23/2017
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <link rel="stylesheet" type="text/css" href="../Stylesheets/LoginStyle.css">
    </head>
    <title>Register</title>
</head>
<body>
<div>Register</div>
<form class="form" method="get" action="registerServlet" >
   Name: <input name="name" required="Please enter a name"><br>
    Email: <input name="email" required="Please enter the email"><br>
    Username: <input name="username" required="Please enter a username"><br>
   Password : <input name="password" type="password" required="Please enter a password"><br>
   Retype Password : <input name="retypepassword" required="Please enter the password"><br>
    <input type="submit" value="Register"><br>
</form>
</body>
</html>
