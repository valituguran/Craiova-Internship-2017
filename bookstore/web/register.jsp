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
    <title>Title</title>
</head>
<body>
<form action="registerServlet" method="post">
    <fieldset style="width: 300px">
        <legend> Register </legend>
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="username" required="required" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="userpass" required="required" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" required="required" /></td>
            </tr>
            <tr>
                <td><input type="submit" name="register" value="Register" /></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
