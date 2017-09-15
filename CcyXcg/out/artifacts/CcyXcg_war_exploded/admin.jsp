<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<<<<<<< HEAD
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
    <link rel="stylesheet" type="text/css" href="../StyleSheet/myAccountStyle.css">
</head>
<body>
    <div class="topnav">
        <a class="active" href="home.jsp">Ccy Xcg </a>
        <a href="home.jsp">Currencies</a>
        <a href="History.jsp">History</a>
        <a href="register.jsp">Register</a>
        <div id="logoutt">
            <form action="logoutServlet" method="post">
                <a>  <input type="submit" id="logout" value="Logout" />   </a>
            </form>
        </div>
    </div>
    <form class="form" method="get" action="ParseHistoryServlet" >
        <input type="submit" value="Insert history in database"><br>
    </form>
=======
    <title>Title</title>
</head>
<body>
<a href="register.jsp">Register an user</a>
<form action="logoutServlet" method="post">
    <input type="submit" value="Logout" />
    <a href="BNRCurrency.jsp">Update the Currency Values</a>
</form>
>>>>>>> 3f388d2b93ed725a76c885622f61a590f6163e4f
</body>
</html>
