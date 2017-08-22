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
<%String realname;
    realname=(String)session.getAttribute("realname");%>
<%int currentpage = PaginationServlet.currentPage;
    int noOfPages = PaginationServlet.noOfPages;
    int recordsPerPage = PaginationServlet.recordsPerPage;
%>
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="/mycontuserServlet"><%=realname%></a>
    <a href="index.jsp">Logout</a>
</div>

<div class="topnav">
    <span style="align:left;cursor:pointer;color:white;text-align:center;font-size: 20px;" onclick="openNav()">&#9776;<%=realname%></span>
    <a href="addbook.jsp">Add books</a>
    <a href="buy.jsp">Cart</a>
</div>
<div class="content">
     <div class="menu-vertical">
    <ul class="breadcrumb">
        <li><a href="products_user.jsp">Home</a></li>
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
<img class="logo" src="../images/logo.jpg">
<div class="products" id="products">
    <%LinkedList list = PaginationServlet.list;%>
    <div class="container">
        <%for( int i=0; i<list.size(); i++){
            Book book = (Book) list.get(i);%>
        <div class="tab-content">
            <h3><%=book.getNume()%></h3>
            <div class="product">
                <img src="<%=book.getImage()%>">
                <form name="model" method="POST" action="/cartadminServlet"><p>Title:
                    <%=book.getNume()%><input type="hidden" name="book" value="<%=book.getNume()%>"></p>
                    <p>Description:
                        ...<input type="hidden" name="description" value="<%=book.getDescription()%>"></p>
                    <p><strong>Quantity</strong>: <input type="text" size="2" value="1" name="quantity"></p>
                    <p>Price<%=book.getPrice()%><input type="hidden" name="price" value="<%=book.getPrice()%>"></p>
                    <button onclick="cart()"><input type="hidden" name="action" value="add">Buy</button>
                </form>
            </div>
        </div>
        <% } %>
    </div>

</div>
<div class="bottom">
    <form  method="POST" action="/paginationServlet">
        <ul class="pagination">
            <li> <input type="submit" onclick="pagination()" name="action" value="Prev" id="prev" ></li>
            <input type="hidden" name="<%=noOfPages%>" id="noOfPages" value="noOfPages">
            <li>Page <input type="hidden" name="currentpage" id="current" value="<%=currentpage%>"><%=currentpage%>/<%=noOfPages+1%></li>
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
</script>
</body>

</html>
