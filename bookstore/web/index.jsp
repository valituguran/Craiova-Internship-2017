<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.Author"%>
<%@ page import="com.ymens.Book" %>
<%@ page import="com.ymens.servlet.ProductsServlet"%>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../styles/style.css">
    <link src="../scripts/file.js">

</head>

<body>

<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="login.jsp">Login</a>
</div>

<div class="topnav">
    <span style="align:left;cursor:pointer;color:white;text-align:center;font-size: 20px;" onclick="openNav()">&#9776;Bookstore</span>
</div>

<div class="content">
    <div class="menu-vertical">
        <ul class="breadcrumb">
            <li><a href="#">Home</a></li>
            <li><a href="#products">Books</a></li>
        </ul>
        <div class="form">
            <h4>Filter</h4>
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
<div class="products" id="products">
        <<img class="logo" src="../images/logo.jpg">
        <%LinkedList list = ProductsServlet.list;%>
        <div class="container">
            <%for( int i=0; i<list.size(); i++){
                    Book book = (Book) list.get(i);%>
            <div class="tab-content">
                <h3><%=book.getNume()%></h3>
                <div class="product">
                    <img src="<%=book.getURLImage()%>">
                    Title:<%=book.getNume()%><input type="hidden" name="book" value="<%=book.getNume()%>"></p>
                        <p>Description:
                            ...<input type="hidden" name="description" value="<%=book.getDescription()%>"></p>
                        <p>Quantity: <input type="text" size="2" value="1" name="quantity"></p>
                        <p>Price<%=book.getPrice()%><input type="hidden" name="price" value="<%=book.getPrice()%>"></p>
                        <button onclick="redirectLogin()"><input type="hidden" name="action" value="add">Buy</button>
                </div>
            </div>
            <% } %>
        </div>
</div>


<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }
    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
    function redirectLogin() {
        var txt;
        var r = confirm("Va rugam sa va logati!");
        if (r == true) {
            window.location="login.jsp";
        } else {
            txt = "You pressed Cancel!";
        }
        document.getElementById("demo").innerHTML = txt;
    }
</script>
</body>

</html>
