<%@ page import="com.ymens.ParseSymbol" %>
<%@ page import="com.ymens.ParseFunction" %><%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 8/23/2017
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/registerStyle.css">
</head>
<body>
<body class="align">
<div class="grid">
    <form action="registerServlet" method="get" class="form login">
        <header class="login__header">
            <h3 class="login__title">Register</h3>
        </header>
        <div class="login__body">
            <div class="form__field">
                <input name="name" type="username" placeholder="Name" required>
            </div>
            <div class="form__field">
                <input name="email" type="username" placeholder="Email" required>
            </div>
            <div class="form__field">
                <input name="username" type="username" placeholder="Username" required>
            </div>
            <div class="form__field">
                <input name="password" type="password" placeholder="Password" required>
            </div>
            <div class="form__field">
                <input name="retypepassword" type="username" placeholder="Retype Password" required>
            </div>
            <div class="form__field">
                <input name="balance" type="username" placeholder="Balance" required>
            </div>
            <div class="form__field">
                Currency :<select name="currency">
                <%ParseSymbol.parsesymb("E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\bnr.xml");%>
                <%for(int i=0;i< ParseSymbol.symbol.size();i++){%>
                <%String pair = (String) ParseSymbol.symbol.get(i);%>
                <option value="<%=pair%>"><%=pair%></option>
                <%}%>
            </select>
            </div>
        </div>
        <footer class="login__footer">
            <input type="submit" value="Register">
        </footer>
    </form>
</div>
</body>
</body>
</html>

