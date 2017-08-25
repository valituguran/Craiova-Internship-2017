<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.Book"%>
<%@ page import="com.ymens.dao.SelectBooksDao" %>
<%@ page import="com.ymens.servlet.ProductsServlet"%>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.ymens.servlet.SelectBooksServlet" %>
<%@ page import="com.ymens.servlet.PaginationServlet" %>
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
<%String text =(String) session.getAttribute("page");
String user =(String) session.getAttribute("name");
    String searchbyauthor;
    String searchbyname;
if(user == null ) {
     searchbyauthor = "/searchbyauthor" + "Servlet";
     searchbyname = "/searchbyname" + "Servlet";
} else {
    String type =(String) session.getAttribute("type");
      searchbyauthor = "/searchbyauthor"+type+"Servlet";
      searchbyname = "/searchbyname"+type+"Servlet";
}
%>
<div class="topnav">
<a href="<%=text%>">Inapoi la produse</a>
</div>
<div class="content">
    <div class="menu-vertical">
        <ul class="breadcrumb">
            <li><a href="<%=text%>">Home</a></li>
            <li><a href="<%=text%>">Books</a></li>
        </ul>
        <div class="form">
            <h4>Filtru</h4>
            <form method="get" action="<%=searchbyauthor%>" id="searchbyauthor">
                <h3>Cautare dupa autor</h3><br>
                <input name="searchbyauthor" type="text" size="40" placeholder="Cauta..." required="required">
            </form>
            <form method="get" action="<%=searchbyauthor%>" id="searchbyname">
                <h3>Cautare dupa numee</h3><br>
                <input name="searchbyname" type="text" size="40" placeholder="Cauta..." required="required">
            </form>
        </div>
    </div>
</div>
<div class="products" id="products">
    <img class="logo" src="../images/logo.jpg">
    <%Book book = (Book) session.getAttribute("viewbook");%>
    <h3><%=book.getNume()%></h3>

                <img src="data:image/jpg;base64,<%=book.getImage()%>" />
                <p><%=book.getAuthor().getName()%></p>
                <p><%=book.getAuthor().getAge()%></p>
                <p><%=book.getAuthor().getDescription()%></p>
                <p><%=book.getAuthor().getNationality()%></p>
                <p>Descriere:<%=book.getDescription()%></p>
                <p></p>
                <p>Cantitate: <input type="text" size="2" value="1" name="quantity"></p>
                <p>Pret<%=book.getPrice()%><input type="hidden" name="price" value="<%=book.getPrice()%>"></p>
                <button onclick="redirectLogin()"><input type="hidden" name="action" value="add">Buy</button>


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
    function pagination() {
        var current = document.getElementById("current");
        var noOfPages = document.getElementById("noOfPages");
        if (current == noOfPages) {
            document.getElementById("prev").style.visibility = "visible";
        } else {
            document.getElementById("next").style.visibility = "hidden";
        }
        if (current == 1) {
            document.getElementById("prev").style.visibility = "hidden";
        } else {
            document.getElementById("next").style.visibility = "visible";
        }
    }
</script>
</body>

</html>