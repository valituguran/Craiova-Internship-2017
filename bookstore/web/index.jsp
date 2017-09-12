<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.spring.beans.Book"%>

<%@ page import="com.ymens.dao.ManageBook" %>
<%@ page import="com.ymens.servlet.PaginationServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ymens.servlet.SelectBooksServlet" %>
<%@ page import="com.ymens.dao.SelectBooksDao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../styles/style.css">
    <link href="../scripts/file.js">
    <style>
    </style>
</head>
<body>
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
            window.location = "login.jsp";
        } else {
            txt = "You pressed Cancel!";
        }
        document.getElementById("demo").innerHTML = txt;
    }
</script>
<%int currentpage = PaginationServlet.currentPage;
     List<Book> list = SelectBooksDao.select();
    int recordsPerPage = PaginationServlet.recordsPerPage;
    int noOfProducts = list.size();
    int noOfPages;
    if(noOfProducts % recordsPerPage== 0) {
        noOfPages = noOfProducts / recordsPerPage + 1;
    }
    else{
        noOfPages = noOfProducts / recordsPerPage;
    }
%>
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="login.jsp">Login</a>
</div>
<div class="topnav">
    <a href="login.jsp">Login</a>
</div>

<div class="content">
    <div class="menu-vertical">
        <ul class="breadcrumb">

        </ul>
        <div class="form">
            <h4>Ordoneaza: </h4>
            <form method="get" action="/filterbypriceServlet" id="filterbyprice">
                <input name="filterasc" class="filter" type="submit" value="Pret crescator" required="required">
                <input type="hidden" name="typelist" value="filterbyprice" required="required">
            </form>
            <form method="get" action="/filterbypriceServlet" >
                <input name="filterdesc" class="filter" type="submit" value="Pret descrescator" required="required">
                <input type="hidden" name="typelist" value="filterbyprice" required="required">
            </form>
            <form method="get" action="/searchbyauthorServlet" id="searchbyauthor">
                <h3>Autor</h3><br>
                <input name="searchbyauthor" type="text" size="40" placeholder="Cauta autor..." required="required">
                <input type="hidden"  name="typelist" value="searchbyauthor" required="required">
            </form>
            <form method="get" action="/searchbynameServlet" id="searchbyname">
                <h3>Titlu</h3><br>
                <input name="searchbyname" type="text" size="40" placeholder="Cauta titlu..." required="required">
                <input type="hidden"  name="typelist" value="searchbyname" required="required">
            </form>
        </div>
    </div>
</div>

<div class="products" id="products">
    <img class="logo" src="../images/logo.jpg">

    <div class="container">
        <%Book book = new Book();
            for( int i=(currentpage-1)*recordsPerPage; i<currentpage*recordsPerPage && i<noOfProducts; i++){%>
        <div class="tab-content">
            <%book = list.get(i);%>
            <form method="get" action="/viewbookServlet" id="">
                <input type="hidden" name="pagetitle" value="index.jsp" class="title">
                <input name="title" class="title" type="submit" value="<%=book.getName()%> ">
            </form>
            <div class="product">
                <img src="data:image/jpg;base64,<%=book.getStrImage(book.getImage())%>" />
                <input class="details" type="text" size="2" value="1" name="quantity">buc
                <p>Pret: <%=book.getPrice()%> lei<input type="hidden" name="price" value="<%=book.getPrice()%>"></p>
                <button onclick="redirectLogin()"><input type="hidden" name="action" value="add">Adauga in cos</button>
            </div>
        </div>
        <% } %>

    </div>
</div>
<div class="bottom">
    <form  method="POST" action="/paginationServlet">
        <input type="hidden" name="page" id="page" value="/index.jsp">
        <ul class="pagination">
            <li> <input type="submit" onclick="pagination()" name="action" value="Prev" id="prev" ></li>
            <input class="details" type="hidden" name="noOfPages" id="noOfPages" value="<%=noOfPages%>">
            <li> <input type="hidden" name="currentpage" id="current" value="<%=currentpage%>"><%=currentpage%>/<%=noOfPages%></li>
            <li> <input class="details" type="submit" onclick="pagination()" name="action" value="Next" id="next"></li>
        </ul>
    </form>
</div>
<script>
</script>
</body>
</html>

