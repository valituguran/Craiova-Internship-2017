<!-----------------------------Java Imports----------------->
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.ymens.ParseServlet" %>
<%@ page import="dao.ParseDao" %>
<%@ page import="com.ymens.Parse" %>
<%@ page import="dao.SearchDao" %>
<%@ page import="com.ymens.SearchServlet" %>
<%@ page import="dao.PaginationDao" %>
<%@ page import="dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!------------------------------------------------------------->
<html>
<head>
  <title>Ccy Xcg</title>
  <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
  <link rel="stylesheet" type="text/css" href="../StyleSheet/myAccountStyle.css">
  <link rel="stylesheet" type="text/css" href="E:\workspace\Craiova-Internship-2017\CcyXcg\web\StyleSheet\tableStyleSheet.css">
</head>
<body>
  <%String name= (String) session.getAttribute("name");
    ParseDao.getcurrency();
    LinkedList pairs =   ParseDao.pairs;
    LinkedList values = ParseDao.values;
    UserDao user = new UserDao();
    int lensearch = (int) session.getAttribute("lenght");
  %>
  <%if(session.getAttribute("name")!=null){%>
  <div class="topnav">
    <a class="active" href="home.jsp">Ccy Xcg </a>
    <a href="home.jsp">Currencies</a>
    <a href="History.jsp">History</a>
    <a href="myAccount.jsp" id="nameandbalance">Hello <%=user.username%> <br> Balance:<%=user.balance%><%=user.currency%> </a>
      <div id="logoutt">
        <form action="logoutServlet" method="post">
          <a>  <input type="submit" id="logout" value="Logout" />   </a>
        </form>
      </div>
  </div>
  <div id="searchandtable">
    <div class="filterbox">
      <div >Filter</div>
      <form action="searchServlet" method="get">
        <input class="search" name="search" type="text" placeholder="Pair name">
        <input type="submit" value="Search">
      </form>
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
  </div>
  </div>
<%}else if(session.getAttribute("name")==null){%>
  <div class="topnav">
    <a class="active" href="home.jsp">Ccy Xcg </a>
    <a href="home.jsp">Currencies</a>
    <a href="History.jsp">History</a>
    <a href="login.jsp">Login</a>
  </div>
  <div id="searchandtable">
  <div class="filterbox">
  <div >Filter</div>
    <form action="searchServlet" method="get">
      <input class="search" name="search" type="text" placeholder="Pair name">
      <input type="submit" value="Search">
    </form>
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
    </div>
  </div>
  <%}%>
</body>
</html>
