<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>


<%@ page import="com.ymens.hibernate.PrintAuthor" %>
<%@ page import="com.ymens.spring.beans.Author"%>
<%@ page import="com.ymens.spring.beans.Book" %>

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
        <div class="form">
            <h4>Ordoneaza: </h4>
            <form method="get" action="/filterbypriceServlet" >
                <input name="filterasc" class="filter" type="submit" value="Pret crescator" required="required">
                <input type="hidden" name="typelist" value="filterbyprice" required="required">
            </form>
            <form method="get" action="/filterbypriceServlet" id="filterbyprice">
                <input name="filterdesc" class="filter" type="submit" value="Pret descrescator" required="required">
                <input type="hidden" name="typelist" value="filterbyprice" required="required">
            </form>
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
    <h3><%=book.getName()%></h3>

                <img src="data:image/jpg;base64,<%=book.getStrImage(book.getImage())%>" />
    <table>
        <tr>
            <th>Titlu:</th>
            <th><%=book.getAuthor_id()%></th>
        </tr>
        <tr>
            <th>Descriere:</th>
            <th> <%=book.getDescription()%></th>
        </tr>
        <tr>
            <th>Autor:</th>
            <%  Author a = new Author();
            a =PrintAuthor.getDetails(book.getAuthor_id());%>
            <th><%=a.getName()%></th>
        </tr>
        <tr>
            <th>Varsta Autor:</th>
            <th><%=a.getAge()%></th>
        </tr>
        <tr>
            <th>Nationatlitate Autor:</th>
            <th><%=a.getNationality()%></th>
        </tr>
        <tr>
            <th>Descriere Autor:</th>
            <th><%=a.getDescription()%></th>
        </tr>
        <tr>
            <th>Pret: </th>
            <th><input type="hidden" name="price" value="<%=book.getPrice()%>"><%=book.getPrice()%></th>
        </tr>
        <tr>
            <th> Cantitate:</th>
            <th><input type="text" size="2" value="1" name="quantity"></th>
        </tr>
    </table>
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