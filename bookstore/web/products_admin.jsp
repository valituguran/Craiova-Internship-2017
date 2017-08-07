<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<%@page import="com.ymens.Book"%>
<%@ page import="sun.awt.image.ImageWatched" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.ymens.servlet.SelectBooksServlet"%>
<%@ page import="com.ymens.dao.SelectBooksDao" %>
<head>
    <title>Products</title>
    <meta charset="iso-8859-1">
    <link rel="stylesheet" href="../styles/layout.css" type="text/css">

    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <!--[if lt IE 9]><script src="scripts/html5shiv.js"></script><![endif]-->
</head>
<body>

<form method="get" action="/searchbyauthoradminServlet">
    <p>Search by author:</p>
    <input type="search" id="searchbyauthor" name="searchbyauthor" required="required">
    <button type="submit">Search</button>
</form>

<form method="get" action="/searchbynameadminServlet">
     Search by name:
    <input type="text" id="searchbyname" name="searchbyname" required="required">
    <button type="submit">Search</button>
</form>

<div class="wrapper row1">
    <header id="header" class="clear">
        <div id="hgroup">
            <h1><a href="#">Bookstore</a></h1>
        </div>
        <nav class="custom-menu">
            <ul>
                <%String realname;
                    realname=(String)session.getAttribute("realname");%>
                <li><a class="target" href="/mycontServlet"><%=realname%></a></li>
                <li><a class="target" href="index.jsp">Logout</a></li>
                <li><a class="target" href="register.jsp">Add users</a></li>
                <li><a class="target" href="addbook.jsp">Add books</a></li>
                <li class="last"><a class="target" href="buy.jsp">(Cart)</a></li>
            </ul>
        </nav>

    </header>
</div>
<!-- content -->
<div class="wrapper row2">

    <div id="container" class="clear">
        <!-- content body -->
        <div class="slider">

            <li><img src="../images/logo.jpg" /></li>

            </ul>
        </div>
    </div

    <%
        LinkedList list = (LinkedList)session.getAttribute("list");%>
    <div class="container">
        <%

            for( int i=0; i<list.size(); i++){
              Book book = (Book) list.get(i);

        %>
        <div id="tab-1" class="tab-content current">
            <div class="product">
                <img src=" ">
            </div>


              <tr>Title
                 <td width="50px">
                 <%=book.getNume()%><br>
             </td>ISBN
                 <td width="50px">
                     <%=book.getIsbn()%><br>
                 </td>Author
                 <td width="50px">
                     <%=book.getAuthor().getDescription()%>
                     <%=book.getAuthor().getAge()%>
                     <%=book.getAuthor().getNationality()%>
                     <br>
                 </td>Description
                 <td width="50px">
                     <%=book.getDescription()%><br>
                 </td>


             </tr>

        </div>
<% } %>
    </table>
    </div>

    </div>
</div>
<!-- footer -->
<div class="wrapper row3">
    <footer id="footer" class="clear">
        <p class="fl_left">Copyright &copy; - All Rights Reserved - <a href="www.ymens.ro"> Homepage</a></p>
        <p class="fl_right"> Ymens Teamnet SRL</p>
    </footer>
</div>
</body>
</html>
