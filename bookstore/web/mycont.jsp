<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<%@page import="com.ymens.Book"%>
<%@ page import="com.ymens.User" %>
<%@ page import="java.util.LinkedList" %>
<head>
    <title>Products</title>
    <meta charset="iso-8859-1">
    <link rel="stylesheet" href="../styles/layout.css" type="text/css">

    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <!--[if lt IE 9]>
    <script src="scripts/html5shiv.js"></script><![endif]-->
</head>
<body>


<div class="wrapper row1">
    <header id="header" class="clear">
        <div id="hgroup">
            <h1><a href="#">Bookstoreeeee</a></h1>
        </div>
        <nav class="custom-menu">
            <ul>

                <li><a class="target" href="index.jsp">Logout</a></li>
                <li class="last"><a class="target" href="buy.jsp">Cos de cumparaturi</a></li>
            </ul>
        </nav>

    </header>
</div>
<!-- content -->
<div class="wrapper row2">

    <div id="container" class="clear">
        <!-- content body -->
        <div class="slider">

            <li><img src="../images/logo.jpg"/></li>

            </ul>
        </div>
    </div

    <%
        User user =(User) session.getAttribute("currentuser");%>
    <div class="container">

        <div id="tab-1" class="tab-content current">
            <div class="product">
                <img src=" ">
            </div>


            <tr>Username
                <td width="50px">
                    <%=user.getUsername()%><br>
                </td>Realname
                <td width="50px">
                    <%=user.getRealname()%><br>
                </td>Email
                <td width="50px">
                    <%=user.getEmail()%>

                    <br>



            </tr>

        </div>

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
