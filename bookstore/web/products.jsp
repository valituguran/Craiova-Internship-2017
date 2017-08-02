<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<%@page import="com.ymens.Book"%>
<%@ page import="sun.awt.image.ImageWatched" %>
<%@ page import="java.util.LinkedList" %>
    <%@ page import="com.ymens.servlet.SelectBooksServlet"%>
    <%@ page import="com.ymens.dao.SelectBooks" %>
<head>
    <title>Products</title>
    <meta charset="iso-8859-1">
    <link rel="stylesheet" href="styles/layout.css" type="text/css">

    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <!--[if lt IE 9]><script src="scripts/html5shiv.js"></script><![endif]-->
</head>
<body>


<div class="wrapper row1">
    <header id="header" class="clear">
        <div id="hgroup">
            <h1><a href="#">Bookstore</a></h1>
        </div>
        <nav class="custom-menu">
            <ul>
                <li><a class="target" href="login.jsp">Login</a></li>
                <li class="last"><a class="target" href="#">Cos de cumparaturi</a></li>
            </ul>
        </nav>

    </header>
</div>
<!-- content -->
<div class="wrapper row2">

    <div id="container" class="clear">
        <!-- content body -->
        <div class="slider">

            <li><img src="images/logo.jpg" /></li>

            </ul>
        </div>
    </div

    <%
        LinkedList list = (LinkedList)session.getAttribute("list");%>
    <div class="container">
<table cellpadding="2px">
    <thead>
    <tr>
        <td>Nume</td>
        <td>ISBN</td>
        <td>Pret</td>
        <td>Descriere</td>
    </tr>
    </thead>

</table>


        <%

            for( int i=0; i<list.size(); i++){
              Book book = (Book) list.get(i);

        %>
        <div id="tab-1" class="tab-content current">
            <div class="product">
             <tr>
                 <td width="50px">
                 <%=book.getNume()%>
             </td>
                 <td width="50px">
                     <%=book.getIsbn()%>
                 </td>
                 <td width="50px">
                     <%=book.getIdAuthor()%>
                 </td>
                 <td width="50px">
                     <%=book.getDescription()%>
                 </td>


             </tr>
            </div>
        </div>
<% } %>
    </table>
    </div>

    </div>
</div>
<!-- footer -->
<div class="wrapper row3">
    <footer id="footer" class="clear">
        <p class="fl_left">Copyright &copy; - All Rights Reserved - <a href="www.roweb.ro"> Homepage</a></p>
        <p class="fl_right"> Ymens Teamnet SRL</p>
    </footer>
</div>
</body>
</html>
