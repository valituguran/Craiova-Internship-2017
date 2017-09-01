<%@ page import="java.util.LinkedList" %>
<%@ page import="com.ymens.ParseServlet" %>
<%@ page import="dao.ParseDao" %>
<%@ page import="com.ymens.Parse" %>
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
<%String name= (String) session.getAttribute("name");%>
<%if(session.getAttribute("name")!=null){%>
<%
  ParseDao.getcurrency();
  LinkedList pairs =   ParseDao.pairs;
  LinkedList values = ParseDao.values;%>

<div class="header">
  <div>
    <ul>
        <a class="titlehref"href="home.jsp">CCy Xcg</a>
      <li><a href="home.jsp">Currencies</a></li>
      <li><a href="History.jsp">History</a></li>


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
  <%
    int lensearch = (int) session.getAttribute("lenght");//lensearch este dimensiunea listei de cautari
  %>
  <%
    int len;//len este indexul paginii
    if(session.getAttribute("page")==null)
    {
      len=1;
    }
    else{
      len = (int) session.getAttribute("page");
    }
  %>
  <div class="table-div">

    <table class="tablesearch" frame="box">

        <tr>
        <th>Pair name</th>
        <th>Value</th>
        <th>Actions</th>
      </tr>

      <%if (lensearch >0){
          LinkedList list = (LinkedList) session.getAttribute("search");
        for (int i=0; i<lensearch; i=i+2){%>
      <tr>
        <th><%=list.get(i)%></th>
        <th><%=list.get(i+1)%></th>
        <th>Buy/Sell</th>
      </tr>
      <!------------------------------------------------------------------------------------------------------------>
      <%}} else if(lensearch==0){%>
         <p class="errorpairname">Pair name doesn't exist</p>
        <%for (int i = PaginationDao.pagination(len); i< PaginationDao.pagination(len+1); i++){%>
        <%  String pair = (String)pairs.get(i);
            Double value = (Double) values.get(i);%>
        <tr>
            <th><%=pair%></th>
            <th><%=value%></th>
            <th>Buy/Sell</th>
        </tr>
        <%}%>
        <%} %>
    </table>
    <form action="PaginationServlet" method="get">
      <input type="submit" name="button1" value="▲" />
      <input type="submit" name="button2" value="▼" />
      <input class="search" name="page" type="text">
    </form>
  </div>
</div>
<%}else if(session.getAttribute("name")==null){%>
<%
  ParseDao.getcurrency();
  LinkedList pairs =   ParseDao.pairs;
  LinkedList values = ParseDao.values;%>

<div class="header">
  <div>
    <ul>
      <a class="titlehref"href="home.jsp">CCy Xcg</a>
      <li><a href="home.jsp">Currencies</a></li>
      <li><a href="History.jsp">History</a></li>
     <a href="login.jsp">Login</a>
    </ul>
  </div>
</div>
<div class="filterbox">
  <div >Filter</div>
  <form action="searchServlet" method="get">
    <input class="search" name="search" type="text" placeholder="Pair name">
    <input type="submit" value="Search">

  </form>
  <%
    int lensearch = (int) session.getAttribute("lenght");//lensearch este dimensiunea listei de cautari
  %>
  <%
    int len;//len este indexul paginii
    if(session.getAttribute("page")==null)
    {
      len=1;
    }
    else{
      len = (int) session.getAttribute("page");
    }
  %>
  <div class="table-div">

    <table class="tablee" frame="box">
      <tr>
        <th>Pair name</th>
        <th>Value</th>
        <th>Actions</th>
      </tr>

      <%if (lensearch >0){
        LinkedList list = (LinkedList) session.getAttribute("search");
        for (int i=0; i<lensearch; i=i+2){%>
      <tr>
        <th><%=list.get(i)%></th>
        <th><%=list.get(i+1)%></th>
        <th>Buy/Sell</th>
      </tr>
      <!------------------------------------------------------------------------------------------------------------>
      <%}} else if(lensearch==0){%>
      <p class="errorpairname">Pair name doesn't exist</p>
      <%for (int i = PaginationDao.pagination(len); i< PaginationDao.pagination(len+1); i++){%>
      <%  String pair = (String)pairs.get(i);
        Double value = (Double) values.get(i);%>
      <tr>
        <th><%=pair%></th>
        <th><%=value%></th>
        <th>Buy/Sell</th>
      </tr>
      <%}%>
      <%} %>


    </table>
    <form action="PaginationServlet" method="get">
      <input type="submit" name="button1" value="▲" />
      <input type="submit" name="button2" value="▼" />
      <input class="search" name="page" type="text">
    </form>
  </div>
</div>
<%}%>
</body>
</html>