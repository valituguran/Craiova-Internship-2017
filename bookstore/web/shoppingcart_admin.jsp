<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>


<%@ page import="com.ymens.dao.CartDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ymens.hibernate.CartItem" %>
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
    <form method="get" action="/mycontadminServlet" >
        <input name="as"  type="submit" value="Detalii cont" required="required">
        <input name="type" type="hidden" value="accountdetails" required="required">
    </form>
    <form method="get" action="/mycontadminServlet" >
        <input name="as"  type="submit" value="Comenziile mele" required="required">
        <input name="type" type="hidden" value="myorders" required="required">
    </form>
    <form method="get" action="/logoutServlet" >
        <input name="logout" type="submit" value="Logout" required="required">
    </form>
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
        <%ArrayList list = (ArrayList)session.getAttribute("cart");%>
        <div class="container">
            <% if (list.size()==0){%>
                <h1>Cosul dumneavoastra este gol!</h1>
            <a href="products_admin.jsp">Toate produsele</a>
            <%}
            else {%>
            <table>
            <tr>
                <th>Nume produs</th>
                <th>Pret</th>
                <th>Cantitate</th>
                <th>Pret total</th>
                <th></th>
            </tr>
            <%for( int i=0; i<list.size(); i++){
                CartItem cartitem = (CartItem) list.get(i);%>
            <tr> <form name="item" method="POST" action="/cartadminServlet">
                <th><%=cartitem.getBook().getName()%></th>
                <input type='hidden' name='name' value="<%=cartitem.getBook().getName()%>">
                <th>  <%=cartitem.getUnitCost()%><input type='hidden' name='price' value="<%=cartitem.getUnitCost()%>"></th>
                <th><input type='text' class="account" name="quantity" value="<%=cartitem.getQuantity()%>"> <input type="submit" name="action" value="modifica"></th>
                <th><%=cartitem.getTotalCost()%></th>
                <th>  <input type="submit" name="action" value="sterge"></th>
                </form>
            </tr>
            <% } %>
            <tr><form name="order" method="POST" action="/orderServlet">
                <th colspan="3"><input type='hidden' name ="orderTotal" value="<%=CartDao.dblOrderTotal%>">
                    <h3>Suma totala:</h3><%=CartDao.getOrderTotal()%><br></th>
                <th colspan="2"> <input class="button" type="submit" value="Plaseaza comanda"></th>
                    <input type="hidden" name="returnpage" value="products_admin.jsp">
            </form>
            </tr>
            </table>
            <a href="products_admin.jsp">Continua cumparaturile</a>
            <%}%>
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
