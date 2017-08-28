<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.CartItem"%>
<%@ page import="com.ymens.dao.CartDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ymens.dao.OrderDao" %>
<%@ page import="com.ymens.servlet.OrderServlet" %>
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
<%String text =(String) session.getAttribute("currentpage");%>

<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="/mycontServlet"><%=realname%></a>
    <a href="index.jsp">Logout</a>
</div>

<div class="topnav">
    <div style="align:left;cursor:pointer;color:white;font-size: 20px;margin:30px;float:left" onclick="openNav()">&#9776;<%=realname%></div>
    <a href="<%=text%>">Inapoi la produse</a>
</div>
<div class="content">
    <div class="menu-vertical">
        <ul class="breadcrumb">
            <li><a href="products_admin.jsp">Home</a></li>
            <li><a href="products_admin.jsp">Books</a></li>
        </ul>

        <div class="form">
            <h4>Ordoneaza: </h4>
            <form method="get" action="/filterbypriceServlet" id="filterbyprice">
                <input name="filterasc" class="filter" type="submit" value="Pret crescator" required="required">
                <input type="hidden" name="typelist" value="filterbyprice" required="required">
            </form>
            <form method="get" action="/filterbypriceServlet" id="filterbyprice">
                <input name="filterdesc" class="filter" type="submit" value="Pret descrescator" required="required">
                <input type="hidden" name="typelist" value="filterbyprice" required="required">
            </form>
            <form method="get" action="/searchbyauthoradminServlet" id="searchbyauthor">
                <h3>Search by author</h3><br>
                <input name="searchbyauthor" type="text" size="40" placeholder="Search..." required="required">
            </form>
            <form method="get" action="/searchbynameadminServlet" id="searchbyname">
                <h3>Search by name</h3><br>
                <input name="searchbyname" type="text" size="40" placeholder="Search..." required="required">
            </form>
        </div>
    </div>
</div>
    <img class="logo" src="../images/logo.jpg">
    <div class="products" id="products">
        <table class="ordertable">
        <tr>
            <th colspan="4"><h3>Comanda dumneavoastra a fost plasata cu succes!</h3></th>
        </tr>
            <tr>
                <th colspan="4"><h3>Produse comandate:</h3></th>
            </tr>
            <tr>
                <th>Nume produs</th>
                <th>Pret</th>
                <th>Cantitate</th>
                <th>Pret total</th>
            </tr>
        <%ArrayList list =(ArrayList) session.getAttribute("order");%>
        <%for( int i=0; i<list.size(); i++){
                CartItem cartitem = (CartItem) list.get(i);%>
            <tr>
                <th><%=cartitem.getBook().getNume()%></th>
                <th><%=cartitem.getBook().getPrice()%></th>
                <th><%=cartitem.getQuantity()%></th>
                <th><%=cartitem.getTotalCost()%></th>
            </tr>
        <% } %>
        </table>
        <h3>Total de plata:</h3><%=OrderServlet.orderTotal%>
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