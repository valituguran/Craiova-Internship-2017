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
        <link rel="stylesheet" type="text/css" href="../StyleSheet/loginStyle.css">
        <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
    </head>
    <title>Login</title>
</head>
<body>
<form class="login" method="get" action="loginServlet" >
   Name: <input name="name" required="Please enter a name"><br>
    Password: <input name="password" type="password" required="Please enter a password"><br>
    <input type="submit" value="Login"><br>
</form>
</body>
</html>
