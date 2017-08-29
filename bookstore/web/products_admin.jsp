<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.Book"%>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List"%>
<%@ page import="com.ymens.servlet.PaginationServlet" %>
<%@ page import="com.ymens.dao.SelectBooksDao" %>

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
<%int currentpage = PaginationServlet.currentPage;
    LinkedList list = (LinkedList)session.getAttribute("list");
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
    <form method="get" action="/mycontadminServlet" >
        <input name="logout"  type="submit" value="<%=realname%>" required="required">
    </form>
    <form method="get" action="/logoutServlet" >
        <input name="logout"  type="submit" value="Logout" required="required">
    </form>
</div>
<div class="topnav">
    <div style="align:left;cursor:pointer;color:white;font-size: 20px;margin:30px;float:left" onclick="openNav()">&#9776;<%=realname%></div>
    <a href="shoppingcart_admin.jsp">Cos de cumparaturi</a>
    <a href="addbook.jsp">Adauga o carte</a>
    <a href="register.jsp">Adauga un utilizator</a>
</div>
<div class="content">
    <div class="menu-vertical">

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
            <form method="get" action="/searchbyauthorServlet" id="searchbyauthor">
                <h3>Cautare dupa autor</h3><br>
                <input name="searchbyauthor" type="text" size="40" placeholder="Cauta..." required="required">
                <input type="hidden"  name="typelist" value="searchbyauthor" required="required">
            </form>
            <form method="get" action="/searchbynameServlet" id="searchbyname">
                <h3>Cautare dupa nume</h3><br>
                <input name="searchbyname" type="text" size="40" placeholder="Cauta..." required="required">
                <input type="hidden"  name="typelist" value="searchbyname" required="required">
            </form>
        </div>
    </div>
</div>
<div class="products" >
    <img class="logo" src="../images/logo.jpg">
    <div class="container">
        <%for( int i=(currentpage-1)*recordsPerPage; i<currentpage*recordsPerPage && i<noOfProducts; i++){
            Book book = (Book) list.get(i);%>
        <div class="tab-content">
            <form method="get" action="/viewbookServlet" id="">
                <input type="hidden" name="pagetitle" value="index.jsp" class="title">
                <input name="title" class="title" type="submit" value="<%=book.getNume()%> ">
            </form>
            <div class="product">
                <img src="data:image/jpg;base64,<%=book.getImage()%>" />
                <form name="model" method="POST" action="/cartadminServlet">
                  <input type="hidden" name="book" value="<%=book.getNume()%>">
                    <input class="details" type="text" size="2" value="1" name="quantity">buc
                    <p> Pret: <%=book.getPrice()%><input type="hidden" name="price" value="<%=book.getPrice()%>"></p>
                    <button onclick="cart()"><input type="hidden" name="action" value="add">Adauga in cos</button>
                </form>
            </div>
        </div>
        <% } %>
    </div>
</div>
<div class="bottom">
    <form  method="POST" action="/paginationServlet">
        <input type="hidden" name="page" id="page" value="/products_admin.jsp">
        <ul class="pagination">
            <li> <input type="submit" onclick="pagination()" name="action" value="Prev" id="prev" ></li>
            <input type="hidden" name="noOfPages" id="noOfPages" value="<%=noOfPages%>">
            <li> <input type="hidden" name="currentpage" id="current" value="<%=currentpage%>"><%=currentpage%>/<%=noOfPages%></li>
            <li> <input type="submit" onclick="pagination()" name="action" value="Next" id="next"></li>
        </ul>
    </form>
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
        var r = confirm("Please login to continue!!");
        if (r == true) {
            window.location="login.jsp";
        } else {
            txt = "You pressed Cancel!";
        }
        document.getElementById("demo").innerHTML = txt;
    }
    function cart() {
        var txt;
        var r = alert("Produs adaugat cu succes in cos.");
        document.getElementById("demo").innerHTML = txt;
    }
    function products(){
        document.getElementById("currentpage").value = "1";
    }
</script>
</body>
</html>


