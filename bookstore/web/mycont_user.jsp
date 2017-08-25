<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.User"%>
<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../styles/style.css">
    <link src="../scripts/file.js">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
</head>

<body>
<%String realname;
    realname=(String)session.getAttribute("realname");%>
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="/mycontuserServlet"><%=realname%></a>
    <a href="index.jsp">Logout</a>
</div>

<div class="topnav">
    <div style="align:left;cursor:pointer;color:white;font-size: 20px;margin:30px;float:left" onclick="openNav()">&#9776;<%=realname%></div>

    <a href="/../shoppingcart.jsp">Cos de cumparaturi</a>
    <a href="addbook.jsp">Adauga carte</a>
</div>
<div class="content">
    <div class="menu-vertical">
        <ul class="breadcrumb">
            <li><a href="products_user.jsp">Prima pagina</a></li>
            <li><a href="products_user.jsp">Produse</a></li>
        </ul>

        <div class="form">
            <h4>Filter</h4>
            <form method="get" action="/searchbyauthoruserServlet" id="searchbyauthor">
                <h3>Cautare dupa autor</h3><br>
                <input name="searchbyauthor" type="text" size="40" placeholder="Cauta..." required="required">
            </form>
            <form method="get" action="/searchbynameuserServlet" id="searchbyname">
                <h3>Cautare dupa nume</h3><br>
                <input name="searchbyname" type="text" size="40" placeholder="Cauta..." required="required">
            </form>
        </div>
    </div>
</div>

    <img class="logo" src="../images/logo.jpg">
    <div class="products" id="products">
        <%
            User user =(User) session.getAttribute("currentuser");%>
        <div class="container">
            <div id="tab-1" class="tab-content current">
                Username:<%=user.getUsername()%><br>
                Realname: <%=user.getRealname()%><br>
                Email:<%=user.getEmail()%><br>
            </div>

    </div>
</div>

<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }
    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
    function cart() {
        var txt;
        var r = alert("Produs adaugat cu succes in cos.");
        document.getElementById("demo").innerHTML = txt;
    }
</script>
</body>

</html>