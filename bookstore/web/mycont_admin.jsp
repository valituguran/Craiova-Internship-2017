<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="com.ymens.dao.History" %>
<%@ page import="com.ymens.spring.beans.User" %>
<%@ page import="java.util.ArrayList" %>

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
    realname=(String)session.getAttribute("realname");
    String name = (String) session.getAttribute("name");
    String type = (String) session.getAttribute("type");
%>
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
    <div style="align:left;cursor:pointer;color:white;font-size: 20px;margin:30px;float:left" onclick="openNav()">&#9776;<%=realname%></div>

    <a href="shoppingcart_admin.jsp">Cos de cumparaturi</a>
    <a href="addbook.jsp">Add books</a>
    <a href="register.jsp">Add users</a>
</div>
<div class="content">
    <div class="menu-vertical">
        <ul class="breadcrumb">
            <li><a href="products_admin.jsp">Prima pagina</a></li>
        </ul>

        <div class="form">
            <h4>Ordoneaza: </h4>
            <form method="get" action="/filterbypriceServlet" >
                <input name="filterasc" class="filter" type="submit" value="Pret crescator" required="required">
                <input type="hidden" name="typelist" value="filterbyprice" required="required">
            </form>
            <form method="get" action="/filterbypriceServlet" >
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
</div>
    <img class="logo" src="../images/logo.jpg">

    <div class="products" id="products">
        <%if(type.equals("accountdetails")){
            User user =(User) session.getAttribute("currentuser");%>

        <div class="container">
            <div id="tab-1" class="tab-content current">
                <img class="imageaccount" src="../images/account.jpg">
                <form name="item" method="POST" action="/updatecontServlet">
                    <table><tr>
                        <th> Username:</th>
                        <th> <input  class="account" type='hidden' name="username" value="<%=user.getUsername()%>"><%=user.getUsername()%></th>
                    </tr>
                    <tr>
                        <th> Nume si prenume:</th>
                        <th><input  class="account" type='text' name="realname" style="color:black;" value="<%=user.getRealName()%>"></th>
                        <th>  <input class="account" type="submit" name="action" value="Modifica"></th>
                    </tr>
                    <tr>
                        <th> Email:</th>
                        <th> <input class="account" type='text' name="email" value="<%=user.getEmail()%>" style="color:black;"></th>
                        <th> <input class="account" type="submit" name="action" value="Modifica adresa de email"></th>
                    </tr>
                    <tr>
                        <th>Parola:</th>
                        <th><input class="account" type='password' name="password" value="<%=user.getPassword()%>"><br></th>
                    </tr>
                    </table>
                </form>
                <%} else if(type.equals("myorders")){

                    ArrayList list =(ArrayList) session.getAttribute("orders");%>
                <table style="width:900px;margin-top:100px;margin-bottom:100px">
                    <tr>
                        <th colspan="3">Istoricul comenziilor mele</th>
                    </tr>
                    <%if (list.size() == 0){%>
                    <tr>
                        <th colspan="3"></th>
                    </tr><tr>
                    <th>Id Comanda</th>
                    <th>Produse comandate</th>
                    <th>Suma totala</th>
                </tr>
                    <% } else {
                       ArrayList list1 = new ArrayList();
                       String string;
                       for(int i=0; i<list.size(); i++){
                           string = String.format("%s%d", "orders", i);
                           list1 = (ArrayList) session.getAttribute(string);
                   %>
                    <tr>
                        <th><%=i+1%></th>
                        <th><% String str;
                            int book_id;
                            for (int j=0; j<list1.size(); j++){%>
                            <%str = list1.get(j).toString();
                            book_id = History.getIdBook(str);%>
                            <p><%=History.getBook(book_id)%></p>
                           <%}%></th>
                        <th><%=(Double)session.getAttribute("price"+string)%></th>
                        </tr>
                            <%}%>
                    <%}%>
                </table>
                <%}%>
            </div>
        </div>
    </div>
<script>
        function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
        }
        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
        }
    </script>
</body>

</html>