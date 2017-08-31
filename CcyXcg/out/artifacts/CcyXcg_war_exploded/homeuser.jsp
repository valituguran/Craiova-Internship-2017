<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 8/28/2017
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ccy Xcg</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
</head>
<body>
<div>
    <div>
        <ul>
            <div class="title">Ccy Xcg</div>
            <li><a href="Aici trebuie adaugata calea catre Currencies">Currencies</a></li>
            <li><a href="Aici trebuie adaugata calea catre history">History</a></li>
            <div>Hello <%=session.getAttribute("name")%></div>
            <form action="logoutServlet" method="post">
                <input type="submit" value="Logout" />
            </form>
        </ul>

    </div>
</div>
</body>
</html>
