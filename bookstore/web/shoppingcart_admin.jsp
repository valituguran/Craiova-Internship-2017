<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.ymens.Book"%>
<%@ page import="com.ymens.dao.CartDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ymens.CartItem" %>
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
    <a href="/mycontadminServlet"><%=realname%></a>
    <a href="index.jsp">Logout</a>
</div>
<div class="topnav">
    <span style="align:left;cursor:pointer;color:white;text-align:center;font-size: 20px;" onclick="openNav()">&#9776;<%=realname%></span>
    <a href="shoppingcart_admin.jsp">Cos de cumparaturi</a>
    <a href="addbook.jsp">Adauga o carte</a>
    <a href="register.jsp">Adauga un utilizator</a>
</div>
<div class="content">
    <ul class="breadcrumb">
        <li><a href="products_admin.jsp">Prima pagina</a></li>
        <li><a href="products_admin.jsp">Produse</a></li>
    </ul>
    <div class="form">
        <h4>Filtru</h4>
        <form method="get" action="/searchbyauthoradminServlet" id="searchbyauthor">
            <h3>Cautare dupa autor</h3><br>
            <input name="searchbyauthor" type="text" size="40" placeholder="Cauta..." required="required">
        </form>
        <form method="get" action="/searchbynameadminServlet" id="searchbyname">
            <h3>Cautare dupa nume</h3><br>
            <input name="searchbyname" type="text" size="40" placeholder="Cauta..." required="required">
        </form>
    </div>
</div>
    <img class="logo" src="../images/logo.jpg">
    <div class="products">
        <%ArrayList list = CartDao.getCartItems();%>
        <div class="container">
            <% if (list.size()==0){%>
                <h1>Cosul dumneavoastra este gol!</h1>
            <a href="products_admin.jsp">Toate produsele</a>

            <%}
            else {%>
            <%for( int i=0; i<list.size(); i++){
                CartItem cartitem = (CartItem) list.get(i);%>
            <form name="item" method="POST" action="/cartadminServlet">
                <p><%=cartitem.getBook().getNume()%></p>
                <input type='hidden' name='name' value="<%=cartitem.getBook().getNume()%>">
                <input type='text' name="quantity" value="<%=cartitem.getQuantity()%>">
                <input type="submit" name="action" value="modifica">
                <br/><input type="submit" name="action" value="sterge">
                <input type='hidden' name='price' value="<%=cartitem.getUnitCost()%>">
                <p>Pret unitar:<%=cartitem.getUnitCost()%></p>
                <p>Cost:<%=cartitem.getTotalCost()%></p>
            </form>
            <% } %>
                <br><pre></pre>
                <input type='hidden' name ="orderTotal" value="<%=CartDao.dblOrderTotal%>">
                <h3>Suma totala:</h3><%=CartDao.getOrderTotal()%><br>
               <form name="order" method="post" action="orderServlet">
                   <button class="button" type="submit">Plaseaza comanda</button>
                   <input type="hidden" name="returnpage" value="products_admin.jsp">
               </form>
            <% }%>
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
