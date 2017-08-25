<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.Book"%>
<%@ page import="com.ymens.servlet.PaginationServlet" %>
<%@ page import="java.util.LinkedList" %>
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
<%int currentpage = PaginationServlet.currentPage;
    LinkedList list = (LinkedList)session.getAttribute("searchbyauthor");
    int recordsPerPage = PaginationServlet.recordsPerPage;
    int noOfProducts = list.size();
    int noOfPages;
    if(noOfProducts/recordsPerPage == 0) {
        noOfPages = noOfProducts / recordsPerPage ;
    }
    else{
        noOfPages = noOfProducts / recordsPerPage+1;
    }
%>
%>
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="login.jsp"></a>
    <a href="#"></a>
</div>
<div class="topnav">
    <span style="align:left;cursor:pointer;color:white;text-align:center;font-size: 20px;" onclick="openNav()">&#9776;</span>
    <a href="login.jsp">Login</a>
</div>
<div class="content">
    <div class="menu-vertical">
    <ul class="breadcrumb">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="#products">Books</a></li>
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
<div class="products" id="products">
    <img class="logo" src="../images/logo.jpg">

    <div class="container">
        <% if(list.size() == 0){%>
        <h3>Nu exista produse cu acest autor</h3>
        <a href="index.jsp">Toate produsele</a>
        <%} else{%>
        <%for( int i=(currentpage-1)*recordsPerPage; i<currentpage*recordsPerPage && i<noOfProducts; i++){
            Book book = (Book) list.get(i);%>
        <div class="tab-content">
            <form method="get" action="/viewbookServlet" id="">
                <input type="hidden" name="pagetitle" value="index.jsp" class="title">
                <input name="title" class="title" type="submit" value="<%=book.getNume()%> ">
            </form>
            <div class="product">
                <img src="data:image/jpg;base64,<%=book.getImage()%>" />
                <input class="details" type="text" size="2" value="1" name="quantity">buc
                Pret: <%=book.getPrice()%><input type="hidden" name="price" value="<%=book.getPrice()%>">
                <button onclick="redirectLogin()"><input type="hidden" name="action" value="add">Adauga in cos</button>
            </div>
        </div>
        <% }%>
    </div>
</div>

<div class="bottom">
    <form  method="POST" action="/paginationServlet">
        <input type="hidden" name="page" id="page" value="/searchbyauthor.jsp">
        <ul class="pagination">
            <li> <input type="submit" onclick="pagination()" name="action" value="Prev" id="prev" ></li>
            <input type="hidden" name="noOfPages" id="noOfPages" value="<%=noOfPages%>">
            <li> <input type="hidden" name="currentpage" id="current" value="<%=currentpage%>"><%=currentpage%>/<%=noOfPages%></li>
            <li> <input type="submit" onclick="pagination()" name="action" value="Next" id="next"></li>
        </ul>
    </form>
</div>
<%}%>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }
    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
    function redirectLogin() {
        var txt;
        var r = confirm("Please login to continue!!");
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