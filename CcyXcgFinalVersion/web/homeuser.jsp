<%@ page import="java.util.LinkedList" %>
<%@ page import="dao.ParseDao" %>
<%@ page import="com.ymens.ParseFunction" %>
<%@ page import="dao.SearchDao" %>
<%@ page import="com.ymens.SearchServlet" %>
<%@ page import="dao.PaginationDao" %>
<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 8/28/2017
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ccy Xcg</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
    <link rel="stylesheet" type="text/css" href="E:\workspace\Craiova-Internship-2017\CcyXcg\web\StyleSheet\tableStyleSheet.css">
</head>
<body>
<%if(session.getAttribute("name")!=null){%>
<%
    ParseDao.getcurrency();
    LinkedList pairs =   ParseDao.pairs;
    LinkedList values = ParseDao.values;%>

<div class="header">
    <div>
        <ul>
            <a class="titlehref"href="home.jsp">CCy Xcg</a>
            <li><a href="Currency.jsp">Currencies</a></li>
            <li><a href="Aici trebuie adaugata calea catre Currencies">Currencies</a></li>
            <li><a href="History.jsp">History</a></li>
            <%String name= (String) session.getAttribute("name");%>
            <li>Hello<%=name%></li>
            <form action="logoutServlet" method="post">
                <input type="submit" value="Logout" />
            </form>
        </ul>
    </div>
</div>
<div class="filterbox">
    <div >Filter</div>
    <form action="searchServlet" method="get">
        <input class="search" name="search" type="text" placeholder="Pair name">
        <input type="submit" value="Search">

    </form>

</div>
<div class="table-div">
    <%
        int len;
        if(session.getAttribute("page")==null)
        {
            len=1;
        }
        else{
            len = (int) session.getAttribute("page");
        }
    %>
    <table class="tablee " frame="box"  id="currencyTable">
        <col span="1" width="300">
        <tr>
            <th>Pair name<a onclick="sortTableAscending()">▲</a><a onclick="sortTableDescending()">▼</a> </th>
            <th>Value</th>
            <th>Actions</th>
        </tr>
        <%for (int i = PaginationDao.pagination(len-1); i< PaginationDao.pagination(len); i++){%>
        <%String pair = (String)pairs.get(i);
            Double value = (Double)values.get(i);%>
        <tr>
            <th><%=pair%></th>
            <th><%=value%></th>
            <th>Buy/Sell</th>
        </tr>
        <%}%>
        <form action="PaginationServlet" method="get">
            <input type="submit" name="button1" value="▲" required="required">
            <input type="submit" name="button2" value="▼" required="required">
            <input class="search" name="page" type="text" value="<%=len%>">
        </form>
    </table>



    <script>
        function sortTableAscending() {
            var table, rows, switching, i, x, y, shouldSwitch;
            table = document.getElementById("currencyTable");
            switching = true;

            while (switching) {

                switching = false;
                rows = table.getElementsByTagName("TR");

                for (i = 1; i < (rows.length - 1); i++) {

                    shouldSwitch = false;

                    x = rows[i].getElementsByTagName("TH")[0];
                    y = rows[i + 1].getElementsByTagName("TH")[0];

                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {

                        shouldSwitch= true;
                        break;
                    }
                }
                if (shouldSwitch) {
                    /*If a switch has been marked, make the switch
                     and mark that a switch has been done:*/
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                }
            }
        }
        function sortTableDescending() {
            var table, rows, switching, i, x, y, shouldSwitch;
            table = document.getElementById("currencyTable");
            switching = true;

            while (switching) {

                switching = false;
                rows = table.getElementsByTagName("TR");

                for (i = 1; i < (rows.length - 1); i++) {

                    shouldSwitch = false;

                    x = rows[i].getElementsByTagName("TH")[0];
                    y = rows[i + 1].getElementsByTagName("TH")[0];

                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {

                        shouldSwitch= true;
                        break;
                    }
                }
                if (shouldSwitch) {
                    /*If a switch has been marked, make the switch
                     and mark that a switch has been done:*/
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                }
            }
        }
    </script>

</div>
<%} else if(session.getAttribute("name")==null){%>
    <a>You need to be login!</a>
    <a href="login.jsp">Login</a>
<%}%>
</body>
</html>
