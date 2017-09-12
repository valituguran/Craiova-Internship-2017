<%@ page import="com.ymens.ParseSymbol" %>
<%@ page import="com.ymens.Parse" %><%--
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
        <link rel="stylesheet" type="text/css" href="../StyleSheet/registerStyle.css">
    </head>
    <title>Register</title>
</head>
<body>

<form class="register" method="get" action="registerServlet" >
    Name: <input name="name" required="Please enter a name"><br>
    Email: <input name="email" required="Please enter the email"><br>
    Username: <input name="username" required="Please enter a username"><br>
    Password : <input name="password" type="password" required="Please enter a password"><br>
    Retype Password : <input name="retypepassword" required="Please enter the password"><br>
    Balance : <input name="balance" required="Please enter the client balance"><br>
    <%String a = "E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\bnr.xml";%>
    Currency :<select name="currency">
    <%ParseSymbol.parsesymb("E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\bnr.xml");%>
    <%for(int i=0;i< ParseSymbol.symbol.size();i++){%>
    <%String pair = (String) ParseSymbol.symbol.get(i);%>
    <option value="<%=pair%>"><%=pair%></option>
    <%}%>
</select>
    <input type="submit" value="Register"><br>
</form>
</body>
</html>
